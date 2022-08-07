//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.repositories;

import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderItemRepository extends DomainRepository<String, OrderItem> {
    Iterable<OrderItem> findByActive(boolean active);

    Iterable<OrderItem> findByProductId(String productId);
}
