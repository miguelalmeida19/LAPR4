package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class ListOrdersForDeliveryUI extends AbstractListUI<ProductOrder>{
    private ListOrdersController theController = new ListOrdersController();

    @Override
    public String headline() {
        return "List Orders dispatched for delivery";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<ProductOrder> elements() {
        return theController.findByOrderStatus(OrderStatus.PREPARED);
    }

    @Override
    protected Visitor<ProductOrder> elementPrinter() {
        return new OrderPrinter();
    }

    @Override
    protected String elementName() {
        return "Order";
    }

    @Override
    protected String listHeader() {
        return String.format("   %30s%30s%30s%30s%30s%30s%30s%30s", "ORDER ID", "CREATION DATE", "FINAL PRICE", "ORDER STATUS", "ITEMS", "CUSTOMER", "CREATED BY", "ROLE");
    }


}
