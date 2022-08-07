//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.repositories;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderRepository extends DomainRepository<String, ProductOrder> {
    Iterable<ProductOrder> findByActive(boolean active);

    Iterable<ProductOrder> findByAgvStatus(AGVStatus agvStatus);

    Iterable<ProductOrder> findByOrderStatus(OrderStatus orderStatus);

    Iterable<ProductOrder> findByAgv(AGV AGV);

    Iterable<ProductOrder> findByCustomerStatus(Customer customer, OrderStatus orderStatus);
}
