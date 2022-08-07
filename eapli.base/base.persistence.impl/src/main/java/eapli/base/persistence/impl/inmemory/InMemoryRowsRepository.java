package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.model.AisleRow;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.base.warehousemanagement.domain.repositories.RowRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRowsRepository extends InMemoryDomainRepository<AisleRow, RowsId> implements RowRepository {
    public InMemoryRowsRepository(){
    }

    public Iterable<AisleRow> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
                });
    }
}
