//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.model;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.utils.PaymentConfirmation;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
Colocar acceptance criteria aqui!
 */

public class OrderBuilder implements DomainFactory<ProductOrder> {

    private static final Logger LOGGER = LogManager.getLogger(OrderBuilder.class);
    private String shippingMethod;
    private OrderStatus orderStatus;
    private String createdOn;
    private Double finalPrice;
    private PaymentMethod paymentMethod;
    private List<OrderItem> orderItems;
    private Customer customer;
    private String systemUser;
    private String createdBy;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public OrderBuilder() {
    }

    public OrderBuilder with(final String shippingMethod, final String paymentMethod, final List<OrderItem> orderItems, final Customer customer) {
        this.withShippingMethod(shippingMethod);
        this.withOrderStatus();
        this.withCreatedOn();
        this.withPaymentMethod(paymentMethod);
        this.withItems(orderItems);
        this.withFinalPrice();
        this.customer = customer;
        this.withSystemUser();
        this.withCreatedBy();

        return this;
    }

    public OrderBuilder withShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public OrderBuilder withOrderStatus() {
        this.orderStatus = OrderStatus.PROCESSING;
        return this;
    }

    public OrderBuilder withCreatedOn() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = now.format(formatter);
        this.createdOn = formatDateTime;
        return this;
    }

    public OrderBuilder withPaymentMethod(String paymentMethod) {
        this.paymentMethod = PaymentMethod.of(paymentMethod);
        return this;
    }

    public OrderBuilder withItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public OrderBuilder withFinalPrice() {

        Double fPrice = 0.0;

        for (OrderItem oi : orderItems){
            fPrice+=oi.totalPrice();
        }

        this.finalPrice = fPrice;
        return this;
    }

    public OrderBuilder withSystemUser() {
        this.systemUser = authz.session().get().authenticatedUser().name().toString();
        return this;
    }

    public OrderBuilder withCreatedBy() {
        this.createdBy = authz.session().get().authenticatedUser().roleTypes().iterator().next().toString();
        return this;
    }

    public ProductOrder build() {
        ProductOrder productOrder;
        productOrder = new ProductOrder(shippingMethod, orderStatus, createdOn, finalPrice, paymentMethod, orderItems, customer, systemUser, createdBy);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new productOrder");
        }

        return productOrder;
    }
}

