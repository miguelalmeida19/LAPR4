package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.model.AGVMaxWeight;
import eapli.base.agvmanagement.domain.model.AGVModel;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryAgvModelRepository extends InMemoryDomainRepository<AGVModel, Long> implements AgvModelRepository {
    public InMemoryAgvModelRepository(){
    }

    public Iterable<AGVModel> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
                });
    }

    @Override
    public Iterable<AGVModel> findByWeigth(AGVMaxWeight agvMaxWeight) {
        return this.match((e) -> {
            return e.maxWeight() == agvMaxWeight;
        });    }
}
