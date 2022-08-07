package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.agvmanagement.application.ListAgvController;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.app.backoffice.console.presentation.AGV.AgvPrinter;
import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.application.UpdateOrderStatusController;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Scanner;

public class UpdateOrderStatusUI extends AbstractUI {
    private final ListOrdersController listOrdersController = new ListOrdersController();
    private final UpdateOrderStatusController updateOrderStatusController = new UpdateOrderStatusController();
    private final ListAgvController listAgvController = new ListAgvController();

    Scanner in = new Scanner(System.in);
    int option = 1;
    int op = 1;

    @Override
    protected boolean doShow() {

        while (option == 1) {

            final Iterable<AGV> agvs = listAgvController.allAgvs();
            final SelectWidget<AGV> selector1 = new SelectWidget<>("Please select an AGV", agvs, new AgvPrinter());
            selector1.show();
            final AGV a = selector1.selectedElement();

            final Iterable<ProductOrder> productOrders = listOrdersController.findByAgv(a);

            while (op == 1) {
                final SelectWidget<ProductOrder> selector = new SelectWidget<>("Select an Order", productOrders, new OrderPrinter());
                selector.show();
                final ProductOrder order = selector.selectedElement();
                updateOrderStatusController.updateStatus(order);
                System.out.println("Do you want to select other order to be updated? (Y/N)");
                String ans = in.nextLine();
                if (ans.equals("N")) {
                    op = 0;
                }
            }

            System.out.println("Do you want to update any other orders that have been prepared by other AGV? (Y/N)");
            String answer = in.nextLine();
            if (answer.equals("N")) {
                option = 0;
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Update Order Status";
    }
}
