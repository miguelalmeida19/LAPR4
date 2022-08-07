package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVDockRepository  extends InMemoryDomainRepository<AGVDock, AGVDockId> implements AGVDockRepository {
    public InMemoryAGVDockRepository(){
    }

    public Iterable<AGVDock> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }
}
