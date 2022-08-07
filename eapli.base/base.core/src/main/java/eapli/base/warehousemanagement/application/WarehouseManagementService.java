package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class WarehouseManagementService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseManagementService(final WarehouseRepository warehouseRepo){
        this.warehouseRepository = warehouseRepo;
    }

    @Transactional
    public Warehouse registerNewWarehouse(final String description, final Long length, final Long width, final Long square, final String unit, final List aislesList, final List agvDockList){
        WarehouseBuilder warehouseBuilder = new WarehouseBuilder();
        warehouseBuilder.with(description, length, width, square, unit, aislesList, agvDockList);
        Warehouse newWarehouse = warehouseBuilder.build();

        return (Warehouse) this.warehouseRepository.save(newWarehouse);
    }

    public Iterable<Warehouse> activeWarehouses(){
        return this.warehouseRepository.findByActive(true);
    }

    public Iterable<Warehouse> deactivatedWarehouses(){
        return this.warehouseRepository.findByActive(false);
    }

    public Iterable<Warehouse> allWarehouses(){
        return this.warehouseRepository.findAll();
    }

    public Optional<Warehouse> warehouseOfIdentity(final String id){
        return this.warehouseRepository.ofIdentity(id);
    }
}
