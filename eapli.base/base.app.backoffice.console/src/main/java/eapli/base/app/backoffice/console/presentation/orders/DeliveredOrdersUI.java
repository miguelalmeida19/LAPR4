package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.application.UpdateOrderStatusController;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Scanner;

public class DeliveredOrdersUI extends AbstractUI {
    private final ListOrdersController listOrdersController = new ListOrdersController();
    private final UpdateOrderStatusController updateOrderStatusController = new UpdateOrderStatusController();

    Scanner in = new Scanner(System.in);
    int option = 1;

    @Override
    protected boolean doShow() {

            final Iterable<ProductOrder> productOrders = listOrdersController.findByOrderStatus(OrderStatus.SHIPPED);
            if(productOrders == null){
                System.out.println("There are no orders on shipped state");
            }else{
                while (option == 1) {
                    final SelectWidget<ProductOrder> selector = new SelectWidget<>("Select an Order", productOrders, new OrderPrinter());
                    selector.show();
                    final ProductOrder order = selector.selectedElement();
                    updateOrderStatusController.updateStatusDelivered(order);
                    System.out.println("Do you want to select other order to be updated? (Y/N)");
                    String ans = in.nextLine();
                    if (ans.equals("N")) {
                        option = 0;
                    }
                }
            }

        return false;
    }

    @Override
    public String headline() {
        return "Update Order to Delivered";
    }
}
