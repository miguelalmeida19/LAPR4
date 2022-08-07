package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.model.AGVMaxWeight;
import eapli.base.agvmanagement.domain.model.AGVModel;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxAgvModelRepository extends JpaAutoTxRepository<AGVModel, Long, Long> implements AgvModelRepository {

    public JpaAutoTxAgvModelRepository(final TransactionalContext autoTx){
        super(autoTx, "agvModelId");
    }

    public JpaAutoTxAgvModelRepository(final String puname, final Map properties){
        super(puname, properties, "agvModelId");
    }

    public Iterable<AGVModel> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<AGVModel> findByWeigth(AGVMaxWeight agvMaxWeight) {
        return this.match("e.agvMaxWeight=:agvMaxWeight", new Object[]{"agvMaxWeight", agvMaxWeight});
    }
}
