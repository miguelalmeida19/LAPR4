package eapli.base.agvdigitaltwin.modules;

import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import eapli.base.spomsp.services.OrdersService;
import eapli.base.warehousemanagement.domain.model.Squares;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ControlSystem {

    int[] sensorSignals = new int[8];
    private static final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();
    private static final OrdersService ordersServer = new OrdersService();
    private AGV agv;
    private List<int[]> agvRoute;

    public ControlSystem(AGV agv) {
        this.agv = agv;
    }


    @Transactional
    public void startExecution(SimulationEngine simulationEngine, BatteryManagementSystem batteryManagementSystem, RoutePlanner routePlanner, Positioning positioning) {
        try {
            Random num = new Random();
            int min = 4000;
            int max = 5000;
            int numFinal = min + num.nextInt(max);

            int count = 0;


            Thread simulationEngineThread1 = new Thread(() -> invokeSimulationEngineWarehouseObstacles(simulationEngine));
            simulationEngineThread1.start();
            simulationEngineThread1.join();

            int time = 0;
            int limit = 1;

            while (time < limit) {
                Thread simulationEngineThread = new Thread(() -> invokeSimulationEngineAgvPositions(simulationEngine));
                simulationEngineThread.start();

                Thread batteryManagementSystemThread = new Thread(() -> invokeBms(batteryManagementSystem));
                batteryManagementSystemThread.start();


                // wait until all obstacles are found
                simulationEngineThread.join();

                System.out.println("\n\n####################################################\n\n");

                int[][] warehouseObstacles = simulationEngine.getWarehouse();
                System.out.println("Warehouse: " + Arrays.deepToString(warehouseObstacles));


                if (count == 0) {
                    agvRoute = invokeRoutePlanning(routePlanner, warehouseObstacles);
                    System.out.println("Path length:" + (agvRoute.size() - 1));
                    agvRoute.stream().forEach(i -> {
                        System.out.println("{" + i[0] + "," + i[1] + "}");
                    });
                }
                count++;
                numFinal--;
                time += 100;

                if (limit == 1) {
                    limit = positioning.findLimit(agvRoute);
                    agvRoute.remove(0);
                }


                agvRoute = positioning.tryToMove(agvRoute, time);


                Thread.sleep(100);
            }
            finishTask();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Transactional
    public void finishTask() {
        try {
            agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.TASKDONE.code, agv.identity());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void invokeSimulationEngineAgvPositions(SimulationEngine simulationEngine) {
        simulationEngine.invokeSimulationEngineAgvPositions();
    }

    @Transactional
    public void invokeSimulationEngineWarehouseObstacles(SimulationEngine simulationEngine) {
        simulationEngine.invokeSimulationEngineWarehouseObstacles();
    }

    @Transactional
    public List<int[]> invokeRoutePlanning(RoutePlanner routePlanner, int[][] warehouseObstacles) {
        routePlanner.setObstacles(warehouseObstacles);
        return routePlanner.recomputesRoute();
    }

    @Transactional
    public int invokePositioning(Positioning positioning, List<int[]> agvRoute, int index, int time) {
        Squares squares = positioning.checkChangePosition(agvRoute, index, time);
        int finalIndex = index;
        if (squares != null) {
            finalIndex += agv.model().maxVelocity();
        }
        return finalIndex;
    }

    public void invokeBms(BatteryManagementSystem batteryManagementSystem) {
        //System.out.print("\nInvoking BatteryManagementSystem from ");
    }

    public void setSensorSignals(int[] sensorSignals) {
        this.sensorSignals = sensorSignals;
    }
}
