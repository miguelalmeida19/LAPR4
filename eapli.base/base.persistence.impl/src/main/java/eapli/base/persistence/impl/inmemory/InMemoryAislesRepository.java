package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAislesRepository extends InMemoryDomainRepository<Aisles, AisleId> implements AisleRepository {
    public InMemoryAislesRepository(){
    }

    public Iterable<Aisles> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
                });
    }
}
