package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import eapli.framework.validations.Invariants;

public class AGVDockRegistry {

    private static AGVDockManagementService agvDockManagementService;

    private AGVDockRegistry(){
    }

    public static void configure(final AGVDockRepository agvDockRepository){
        agvDockManagementService = new AGVDockManagementService(agvDockRepository);
    }

    public static AGVDockManagementService agvDockManagementService(){
        Invariants.nonNull(agvDockManagementService);
        return agvDockManagementService;
    }
}
