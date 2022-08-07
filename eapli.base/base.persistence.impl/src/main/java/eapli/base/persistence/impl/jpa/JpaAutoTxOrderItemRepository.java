//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.Productid;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxOrderItemRepository extends JpaAutoTxRepository<OrderItem, String, String> implements OrderItemRepository {
    public JpaAutoTxOrderItemRepository(final TransactionalContext autoTx) {
        super(autoTx, "orderItemId");
    }

    public JpaAutoTxOrderItemRepository(final String puname, final Map properties) {
        super(puname, properties, "orderItemId");
    }

    public Iterable<OrderItem> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<OrderItem> findByProductId(String productId) {
        Productid productid = Productid.valueOf(productId);
        return this.match("e.product_productid=:productid", new Object[]{"product_productid", productid});
    }
}
