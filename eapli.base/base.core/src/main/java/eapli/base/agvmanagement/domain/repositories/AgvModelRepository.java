package eapli.base.agvmanagement.domain.repositories;

import eapli.base.agvmanagement.domain.model.AGVMaxWeight;
import eapli.base.agvmanagement.domain.model.AGVModel;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.framework.domain.repositories.DomainRepository;

public interface AgvModelRepository extends DomainRepository<Long, AGVModel> {
    Iterable<AGVModel> findByActive(boolean active);
    Iterable<AGVModel> findByWeigth(AGVMaxWeight agvMaxWeight);
}
