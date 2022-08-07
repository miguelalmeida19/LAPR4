//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.model;

import eapli.base.productmanagement.domain.model.*;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/*
Colocar acceptance criteria aqui!
 */

public class OrderItemBuilder implements DomainFactory<OrderItem> {

    private static final Logger LOGGER = LogManager.getLogger(OrderBuilder.class);
    private Integer quantity;
    private Double totalPrice;
    private Product product;

    public OrderItemBuilder() {
    }

    public OrderItemBuilder with(final String quantity, final Product product) {
        this.withQuantity(quantity);
        this.withProduct(product);
        this.withTotalPrice();
        return this;
    }

    public OrderItemBuilder withQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
        return this;
    }

    public OrderItemBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public OrderItemBuilder withTotalPrice() {
        this.totalPrice = quantity * Double.parseDouble(product.price().toString());
        return this;
    }

    public OrderItem build() {
        OrderItem orderItem;
        orderItem = new OrderItem(quantity, totalPrice, product);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new order item");
        }

        return orderItem;
    }
}
