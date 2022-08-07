package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.ordermanagement.domain.domain.model.ProductOrder;

public class UpdateOrderStatusController {

    private final OrderManagementService orderManagementService = OrderRegistry.orderService();

    public void updateStatus(ProductOrder order){
        orderManagementService.setOrderStatus(order);
    }

    public void updateStatusDelivered(ProductOrder order){
        orderManagementService.setOrderStatusDelivered(order);
    }
}
