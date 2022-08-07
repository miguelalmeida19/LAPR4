package eapli.base.warehousemanagement.domain.repositories;

import eapli.base.warehousemanagement.domain.model.AisleRow;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.framework.domain.repositories.DomainRepository;

public interface RowRepository extends DomainRepository<RowsId, AisleRow> {
    Iterable<AisleRow> findByActive(boolean active);
}
