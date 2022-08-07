package eapli.base.persistence.impl.jpa;

import eapli.base.warehousemanagement.domain.model.Warehouse;
import eapli.base.warehousemanagement.domain.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.Map;

public class JpaAutoTxWarehouseRepository extends JpaAutoTxRepository<Warehouse, String, String> implements WarehouseRepository {

    public JpaAutoTxWarehouseRepository(final TransactionalContext autoTx){
        super(autoTx, "warehouseId");
    }

    public JpaAutoTxWarehouseRepository(final String puname, final Map properties){
        super(puname, properties, "warehouseId");
    }

    public Iterable<Warehouse> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
