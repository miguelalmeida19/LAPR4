package eapli.base.warehousemanagement.domain.repositories;

import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVDockRepository extends DomainRepository<AGVDockId, AGVDock> {
    Iterable<AGVDock> findByActive(boolean active);
}
