package eapli.base.warehousemanagement.domain.repositories;

import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.framework.domain.repositories.DomainRepository;

public interface WarehouseRepository extends DomainRepository<String, Warehouse> {
    Iterable<Warehouse> findByActive(boolean active);
}
