package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.SquareRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxSquaresRepository extends JpaAutoTxRepository<Squares, Long, Long> implements SquareRepository {

    public JpaAutoTxSquaresRepository(final TransactionalContext autoTx){
        super(autoTx, "squaresId");
    }

    public JpaAutoTxSquaresRepository(final String puname, final Map properties){
        super(puname, properties, "squaresId");
    }

    public Iterable<Squares> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
