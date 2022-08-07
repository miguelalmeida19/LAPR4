package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.RowRepository;
import eapli.framework.validations.Invariants;

public final class RowRegistry {

    private static RowManagementService rowManagementService;

    private RowRegistry(){
    }

    public static void configure(final RowRepository rowRepository){
        rowManagementService = new RowManagementService(rowRepository);
    }

    public static RowManagementService rowManagementService(){
        Invariants.nonNull(rowManagementService);
        return rowManagementService;
    }
}
