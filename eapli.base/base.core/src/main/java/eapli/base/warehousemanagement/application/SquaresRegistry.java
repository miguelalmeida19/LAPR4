package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.SquareRepository;
import eapli.framework.validations.Invariants;

public final class SquaresRegistry {

    private static SquaresManagementService squaresManagementService;

    private SquaresRegistry(){
    }

    public static void configure(final SquareRepository squareRepository){
        squaresManagementService = new SquaresManagementService(squareRepository);
    }

    public static SquaresManagementService squaresManagementService(){
        Invariants.nonNull(squaresManagementService);
        return squaresManagementService;
    }
}
