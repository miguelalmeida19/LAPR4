package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.ordermanagement.domain.domain.model.OrderDto;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class CheckStatusOfOrdersUI extends AbstractUI {

    private CheckStatusOfOrdersController theController = new CheckStatusOfOrdersController();

    @Override
    protected boolean doShow() {

        List<OrderDto> orders = (List<OrderDto>) theController.getCustomerOrdersStatus();
        System.out.printf("   %30s%30s%30s\n", "ORDER ID", "FINAL PRICE","ORDER STATUS");
        for (OrderDto orderDto : orders){
            System.out.printf("   %30s%30s%30s\n", orderDto.getOrderId(), orderDto.getFinalPrice(), orderDto.getOrderStatus());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Check the status of my open orders";
    }
}
