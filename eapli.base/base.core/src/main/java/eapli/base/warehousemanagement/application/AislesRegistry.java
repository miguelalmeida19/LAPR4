package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.validations.Invariants;

public final class AislesRegistry {

    private static AislesManagementService aislesManagementService;

    private AislesRegistry(){
    }

    public static void configure(final AisleRepository aisleRepository){
        aislesManagementService = new AislesManagementService(aisleRepository);
    }

    public static AislesManagementService aislesManagementService(){
        Invariants.nonNull(aislesManagementService);
        return aislesManagementService;
    }
}
