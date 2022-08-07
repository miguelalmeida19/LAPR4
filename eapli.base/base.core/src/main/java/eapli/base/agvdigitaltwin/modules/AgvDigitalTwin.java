package eapli.base.agvdigitaltwin.modules;

import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.base.ordermanagement.domain.domain.application.OrderRegistry;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import eapli.base.warehousemanagement.domain.model.Squares;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

public class AgvDigitalTwin implements Runnable{
    private static final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();

    private final ControlSystem controlSystem;
    private final BatteryManagementSystem batteryManagementSystem;
    private final Positioning positioning;
    private final RoutePlanner routePlanner;
    private final Sensors sensors;
    private final SimulationEngine simulationEngine;

    private final AGV agv;

    private Squares currentPosition;

    public AgvDigitalTwin(AGV agv){
        this.agv = agv;
        this.currentPosition = agv.agvPosition();
        this.batteryManagementSystem = new BatteryManagementSystem();
        this.positioning = new Positioning(agv);
        this.routePlanner = new RoutePlanner(agv);
        this.sensors = new Sensors(currentPosition);
        this.simulationEngine = new SimulationEngine();
        this.controlSystem = new ControlSystem(agv);
        this.currentPosition = agv.agvPosition();
    }

    @Override
    public void run() {

        try {
            controlSystem.startExecution(simulationEngine, batteryManagementSystem, routePlanner, positioning);
        } catch (Exception ignored) {
        }
    }





    public SimulationEngine getSimulationEngine() {
        return simulationEngine;
    }

    public RoutePlanner getRoutePlanner() {
        return routePlanner;
    }

    public BatteryManagementSystem getBatteryManagementSystem() {
        return batteryManagementSystem;
    }

    public AGV getAgv() {
        return agv;
    }

    public static AgvDigitalTwinService getAgvDigitalTwinService() {
        return agvDigitalTwinService;
    }

    public Positioning getPositioning() {
        return positioning;
    }

    public Sensors getSensors() {
        return sensors;
    }

    public Squares getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Squares currentPosition) {
        this.currentPosition = currentPosition;
    }
}
