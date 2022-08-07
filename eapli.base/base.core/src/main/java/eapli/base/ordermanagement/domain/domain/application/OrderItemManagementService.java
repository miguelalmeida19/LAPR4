//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.OrderItemBuilder;
import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.base.productmanagement.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class OrderItemManagementService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemManagementService(final OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderItem addNewOrderItem(final String quantity, final Product product) {
        OrderItemBuilder orderItemBuilder = new OrderItemBuilder();
        orderItemBuilder.with(quantity, product);
        OrderItem newOrderItem = orderItemBuilder.build();
        return this.orderItemRepository.save(newOrderItem);
    }

    public Iterable<OrderItem> activeOrderItems() {
        return this.orderItemRepository.findByActive(true);
    }

    public Iterable<OrderItem> deactivatedOrderItems() {
        return this.orderItemRepository.findByActive(false);
    }

    public Iterable<OrderItem> allOrderItems() {
        return this.orderItemRepository.findAll();
    }

    public Optional<OrderItem> orderItemOfIdentity(final String code) {
        return this.orderItemRepository.ofIdentity(code);
    }

    public OrderItemRepository getOrderItemRepository() {
        return orderItemRepository;
    }
}
