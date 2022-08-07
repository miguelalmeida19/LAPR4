package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.base.warehousemanagement.domain.model.Square;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.SquareRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySquaresRepository extends InMemoryDomainRepository<Squares, Long> implements SquareRepository {
    public InMemorySquaresRepository(){
    }

    public Iterable<Squares> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
                });
    }
}
