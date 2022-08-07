package eapli.base.warehousemanagement.domain.repositories;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.framework.domain.repositories.DomainRepository;

public interface AisleRepository extends DomainRepository<AisleId, Aisles> {
    Iterable<Aisles> findByActive(boolean active);
}
