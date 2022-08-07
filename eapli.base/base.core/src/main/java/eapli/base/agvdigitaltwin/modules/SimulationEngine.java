package eapli.base.agvdigitaltwin.modules;

import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import eapli.base.spomsp.services.OrdersService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationEngine {
    private static final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();
    private static final OrdersService ordersServer = new OrdersService();
    private ArrayList<String> agvsPositions;
    private ArrayList<Object> warehouseObstacles;
    private int[][] warehouse = new int[18][20];
    public SimulationEngine(){}

    @Transactional
    public void invokeSimulationEngineAgvPositions(){
        agvsPositions = (ArrayList<String>) askForAllAgvsPositions();
        for (String pos : agvsPositions){
            warehouse[Integer.parseInt(pos.split(" ")[1])][Integer.parseInt(pos.split(" ")[0])] = 1;
        }
    }

    @Transactional
    public void invokeSimulationEngineWarehouseObstacles(){
        warehouseObstacles = (ArrayList<Object>) askForWarehouseObstacles();
        int width = warehouseObstacles.size();
        int length = warehouseObstacles.get(0).toString().split(", ").length;
        warehouse = new int[width][length];
        for (int i=0; i<width; i++){
            for (int j=0; j<length; j++){
                int number = Integer.parseInt(warehouseObstacles.get(i).toString().split(", ")[j].replace("[","").replace("]",""));
                if (number!=0){ // Obstacle
                    warehouse[i][j] = 1;
                } else {
                    warehouse[i][j] = 0;
                }
            }
        }
    }

    @Transactional
    public Object askForAllAgvsPositions() {
        Object list;
        try {
            // Asks AGV Manager the positions of other AGV running
            list = agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.RUNNING_AGVS.code, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Transactional
    public Object askForWarehouseObstacles(){
        Object array;
        try {
            // Ask Orders Server about the Warehouse physical obstacles
            array = ordersServer.sendMessageToServer(SPOMSP.Code.WAREHOUSE_OBSTACLES.code, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return array;
    }

    public ArrayList<String> getAgvsPositions() {
        return agvsPositions;
    }

    public ArrayList<Object> getWarehouseObstacles() {
        return warehouseObstacles;
    }

    public int[][] getWarehouse() {
        return warehouse;
    }
}
