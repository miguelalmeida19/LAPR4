package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.ordermanagement.domain.domain.model.ProductOrder;

import java.io.IOException;

public class AssignAgvOrderController {

    private final OrderManagementService orderService = OrderRegistry.orderService();

    public void setAgvOrder(ProductOrder order) throws IOException {
        orderService.setOrderAgv(order);
    }
}
