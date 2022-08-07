package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.validations.Invariants;

public final class WarehouseRegistry {

    private static WarehouseManagementService warehouseService;

    private WarehouseRegistry(){
    }

    public static void configure(final WarehouseRepository warehouseRepo){
        warehouseService = new WarehouseManagementService(warehouseRepo);
    }

    public static WarehouseManagementService warehouseService(){
        Invariants.nonNull(warehouseService);
        return warehouseService;
    }
}
