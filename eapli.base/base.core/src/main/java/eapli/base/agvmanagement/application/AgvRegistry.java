package eapli.base.agvmanagement.application;


import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.framework.validations.Invariants;

public class AgvRegistry {

    private static AgvManagementService agvService;

    private AgvRegistry() {
    }

    public static void configure(final AGVRepository agvRepo) {
        agvService = new AgvManagementService(agvRepo);
    }

    public static AgvManagementService agvService() {
        Invariants.nonNull(agvService);
        return agvService;
    }
}
