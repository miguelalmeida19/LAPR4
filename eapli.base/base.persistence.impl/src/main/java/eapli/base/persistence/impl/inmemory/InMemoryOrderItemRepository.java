//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderItemRepository extends InMemoryDomainRepository<OrderItem, String> implements OrderItemRepository {
    public InMemoryOrderItemRepository() {
    }

    public Iterable<OrderItem> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<OrderItem> findByProductId(String productId) {
        return null;
    }
}
