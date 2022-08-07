package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.base.warehousemanagement.domain.repositories.AisleRepository;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxAislesRepository extends JpaAutoTxRepository<Aisles, AisleId, AisleId> implements AisleRepository {

    public JpaAutoTxAislesRepository(final TransactionalContext autoTx){
        super(autoTx, "aislesId");
    }

    public JpaAutoTxAislesRepository(final String puname, final Map properties){
        super(puname, properties, "aislesId");
    }

    public Iterable<Aisles> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
