//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.ordermanagement.domain.domain.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.domain.model.*;
import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.AgvDigitalTwinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class OrderManagementService {
    private final OrderRepository orderRepository;
    private final AgvDigitalTwinService agvDigitalTwinService = new AgvDigitalTwinService();

    @Autowired
    public OrderManagementService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public ProductOrder addNewOrder(final String shippingMethod, final String paymentMethod, final List<OrderItem> orderItems, final Customer customer) throws IOException {
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.with(shippingMethod, paymentMethod, orderItems, customer);
        ProductOrder newProductOrder = orderBuilder.build();

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr;
        ProductOrder productOrder = this.orderRepository.save(newProductOrder);
        customer.orders().add(productOrder);
        //PersistenceContext.repositories().customers().save(customer);
        jsonStr = Obj.writeValueAsString(productOrder.identity());
        agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.TASKCREATED.code, jsonStr);
        return productOrder;
    }

    public Iterable<ProductOrder> activeOrders() {
        return this.orderRepository.findByActive(true);
    }

    public Iterable<ProductOrder> deactivatedOrders() {
        return this.orderRepository.findByActive(false);
    }

    public Iterable<ProductOrder> allOrders() {
        return this.orderRepository.findAll();
    }

    public Iterable<ProductOrder> findByAgvStatus(AGVStatus agvStatus){
        return this.orderRepository.findByAgvStatus(agvStatus);
    }

    public Optional<ProductOrder> orderOfIdentity(final String code) {
        return this.orderRepository.ofIdentity(code);
    }

    public void setOrderAgv(ProductOrder order) throws IOException {
        System.out.println("forced Order identity: " + order.identity());
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(order.identity());
        agvDigitalTwinService.sendMessageToServer(SPOMSP.Code.FORCETASK.code, jsonStr);
    }

    public Iterable<ProductOrder> findByOrderStatus(OrderStatus orderStatus){
        return this.orderRepository.findByOrderStatus(orderStatus);
    }

    public void setOrderStatus(ProductOrder order){
        order.setOrderStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }

    public void setOrderStatusDelivered(ProductOrder order){
        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
    }

    public Iterable<ProductOrder> findByAgv(AGV AGV){
        return this.orderRepository.findByAgv(AGV);
    }

    public Iterable<ProductOrder> findByCustomerStatus(Customer customer, OrderStatus orderStatus){
        return this.orderRepository.findByCustomerStatus(customer, orderStatus);
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}
