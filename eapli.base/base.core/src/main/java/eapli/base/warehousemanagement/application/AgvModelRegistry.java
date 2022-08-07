package eapli.base.warehousemanagement.application;

import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.framework.validations.Invariants;

public final class AgvModelRegistry {

    private static AgvModelManagementService agvModelManagementService;

    private AgvModelRegistry(){
    }

    public static void configure(final AgvModelRepository agvModelRepository){
        agvModelManagementService = new AgvModelManagementService(agvModelRepository);
    }

    public static AgvModelManagementService agvModelManagementService(){
        Invariants.nonNull(agvModelManagementService);
        return agvModelManagementService;
    }
}
