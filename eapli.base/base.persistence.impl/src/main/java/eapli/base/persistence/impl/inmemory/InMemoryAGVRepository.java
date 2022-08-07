package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, String> implements AGVRepository {
    public InMemoryAGVRepository() {
    }

    public Iterable<AGV> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<AGV> findByAgvWorkState(AGVWorkState agvWorkState) {
        return this.match((e) -> {
            return e.agvWorkState() == agvWorkState;
        });
    }
}