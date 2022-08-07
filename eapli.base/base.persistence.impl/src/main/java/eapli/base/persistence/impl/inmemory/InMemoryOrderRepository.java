//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<ProductOrder, String> implements OrderRepository {
    public InMemoryOrderRepository() {
    }

    public Iterable<ProductOrder> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<ProductOrder> findByAgvStatus(AGVStatus agvStatus) {
        return this.match((e) -> {
            return e.agvStatus() == agvStatus;
        });
    }

    @Override
    public Iterable<ProductOrder> findByOrderStatus(OrderStatus orderStatus) {
        return this.match((e) -> {
            return e.orderStatus() == orderStatus;
        });
    }

    @Override
    public Iterable<ProductOrder> findByAgv(AGV AGV) {
        return this.match((e) -> {
            return e.agv() == AGV;
        });
    }

    @Override
    public Iterable<ProductOrder> findByCustomerStatus(Customer customer, OrderStatus orderStatus) {
        return this.match((e) -> {
            return (e.getCustomer() == customer) && (e.orderStatus()==orderStatus);
        });
    }


}
