package eapli.base.warehousemanagement.domain.repositories;

import eapli.base.warehousemanagement.domain.model.AisleRow;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.framework.domain.repositories.DomainRepository;

public interface SquareRepository extends DomainRepository<Long, Squares> {
    Iterable<Squares> findByActive(boolean active);
}
