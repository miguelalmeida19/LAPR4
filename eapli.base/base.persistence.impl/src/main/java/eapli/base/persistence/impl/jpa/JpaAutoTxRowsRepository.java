package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.model.AisleRow;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.base.warehousemanagement.domain.repositories.RowRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxRowsRepository extends JpaAutoTxRepository<AisleRow, RowsId, RowsId> implements RowRepository {

    public JpaAutoTxRowsRepository(final TransactionalContext autoTx){
        super(autoTx, "rowsId");
    }

    public JpaAutoTxRowsRepository(final String puname, final Map properties){
        super(puname, properties, "rowsId");
    }

    public Iterable<AisleRow> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
