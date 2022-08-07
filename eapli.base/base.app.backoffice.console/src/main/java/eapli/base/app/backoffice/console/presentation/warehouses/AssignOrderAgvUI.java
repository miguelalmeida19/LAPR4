package eapli.base.app.backoffice.console.presentation.warehouses;

import eapli.base.agvmanagement.application.ListAgvController;
import eapli.base.app.backoffice.console.presentation.orders.OrderPrinter;
import eapli.base.ordermanagement.domain.domain.application.AssignAgvOrderController;
import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;

public class AssignOrderAgvUI extends AbstractUI {
    private final ListOrdersController theController = new ListOrdersController();
    private final ListAgvController theController1 = new ListAgvController();
    private final AssignAgvOrderController assignAgvOrderController = new AssignAgvOrderController();

    @Override
    protected boolean doShow() {
        final Iterable<ProductOrder> productOrders = theController.findByAgvStatus(AGVStatus.WAITING);
        final SelectWidget<ProductOrder> selector = new SelectWidget<>("Select an Order", productOrders, new OrderPrinter());
        selector.show();
        final ProductOrder order = selector.selectedElement();

        try {
            assignAgvOrderController.setAgvOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Force an Order to be prepared";
    }
}
