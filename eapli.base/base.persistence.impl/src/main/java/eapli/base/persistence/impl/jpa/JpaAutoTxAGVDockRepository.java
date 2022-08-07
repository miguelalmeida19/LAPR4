package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxAGVDockRepository  extends JpaAutoTxRepository<AGVDock, AGVDockId, AGVDockId> implements AGVDockRepository {

    public JpaAutoTxAGVDockRepository(final TransactionalContext autoTx){
        super(autoTx, "agvDocksId");
    }

    public JpaAutoTxAGVDockRepository(final String puname, final Map properties){
        super(puname, properties, "agvDocksId");
    }

    public Iterable<AGVDock> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
