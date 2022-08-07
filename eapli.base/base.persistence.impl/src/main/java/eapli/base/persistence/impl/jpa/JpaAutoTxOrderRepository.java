//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.List;
import java.util.Map;

public class JpaAutoTxOrderRepository extends JpaAutoTxRepository<ProductOrder, String, String> implements OrderRepository {
    public JpaAutoTxOrderRepository(final TransactionalContext autoTx) {
        super(autoTx, "orderId");
    }

    public JpaAutoTxOrderRepository(final String puname, final Map properties) {
        super(puname, properties, "orderId");
    }

    public Iterable<ProductOrder> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<ProductOrder> findByAgvStatus(AGVStatus agvStatus) {
        return this.match("e.agvStatus=:agvStatus", new Object[]{"agvStatus", agvStatus});
    }

    @Override
    public Iterable<ProductOrder> findByOrderStatus(OrderStatus orderStatus) {
        return this.match("e.orderStatus=:orderStatus", new Object[]{"orderStatus", orderStatus});
    }

    @Override
    public Iterable<ProductOrder> findByAgv(AGV AGV){
        return this.match("e.AGV=:AGV", new Object[]{"AGV", AGV});
    }

    @Override
    public Iterable<ProductOrder> findByCustomerStatus(Customer customer, OrderStatus orderStatus){
        List<ProductOrder> list = this.match("e.customer=:customer", new Object[]{"customer", customer});
        List<ProductOrder> list1 = this.match("e.orderStatus!=:orderStatus", new Object[]{"orderStatus", orderStatus});
        list.retainAll(list1);
        return list;
    }

}
