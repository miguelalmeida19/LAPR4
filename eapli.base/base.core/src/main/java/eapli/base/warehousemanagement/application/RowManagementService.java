package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class RowManagementService {

    private final RowRepository rowRepository;
    private final SquaresManagementService squaresManagementService = SquaresRegistry.squaresManagementService();

    @Autowired
    public RowManagementService(final RowRepository aisleRepository){
        this.rowRepository = aisleRepository;
    }

    @Transactional
    public AisleRow registerNewRow(final RowsId rowsId, final Squares begin, final Squares end, final Shelf shelf){
        AisleRow aisleRow = new AisleRow(rowsId, begin, end, shelf);
        return this.rowRepository.save(aisleRow);
    }

    public Iterable<AisleRow> activeRows(){
        return this.rowRepository.findByActive(true);
    }

    public Iterable<AisleRow> deactivatedRows(){
        return this.rowRepository.findByActive(false);
    }

    public Iterable<AisleRow> allRows(){
        return this.rowRepository.findAll();
    }

    public Optional<AisleRow> rowOfIdentity(final RowsId id){
        return this.rowRepository.ofIdentity(id);
    }
}
