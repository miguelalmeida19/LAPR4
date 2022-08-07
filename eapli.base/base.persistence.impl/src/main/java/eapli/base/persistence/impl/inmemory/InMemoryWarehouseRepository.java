package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryWarehouseRepository extends InMemoryDomainRepository<Warehouse, String> implements WarehouseRepository {
    public InMemoryWarehouseRepository(){
    }

    public Iterable<Warehouse> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
                });
    }
}
