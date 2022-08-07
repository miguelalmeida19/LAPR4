package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.agvmanagement.domain.model.AGVWorkState;
import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTXAGVRepository extends JpaAutoTxRepository<AGV, String, String> implements AGVRepository {

    public JpaAutoTXAGVRepository(final TransactionalContext autoTx){
        super(autoTx, "agvIdentifier");
    }

    public JpaAutoTXAGVRepository(final String puname, final Map properties){
        super(puname, properties, "agvIdentifier");
    }

    public Iterable<AGV> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<AGV> findByAgvWorkState(AGVWorkState agvWorkState) {
        return this.match("e.agvWorkState=:agvWorkState", new Object[]{"agvWorkState", agvWorkState});
    }
}