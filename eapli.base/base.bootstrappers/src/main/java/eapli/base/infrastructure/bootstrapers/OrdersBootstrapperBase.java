package eapli.base.infrastructure.bootstrapers;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.application.AddOrderController;
import eapli.base.ordermanagement.domain.domain.application.AddOrderItemController;
import eapli.base.ordermanagement.domain.domain.application.ListOrderItemsController;
import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.domain.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrdersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersBootstrapperBase.class);

    final AddOrderController addOrderController = new AddOrderController();
    final ListOrdersController listOrdersController = new ListOrdersController();
    final AddOrderItemController addOrderItemController = new AddOrderItemController();
    final ListOrderItemsController listOrderItemsController = new ListOrderItemsController();

    public OrdersBootstrapperBase(){
        super();
    }

    protected OrderItem registerOrderItem(final String quantity, final Product product){
        OrderItem ot = null;
        try{
            ot = addOrderItemController.addOrderItem(quantity, product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ot;
    }

    protected ProductOrder registerOrder(final String shippingMethod, final String paymentMethod, final List<OrderItem> orderItems, final Customer customer){
        ProductOrder po = null;
        try{
            po = addOrderController.addOrder(shippingMethod, paymentMethod, orderItems, customer);
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return po;
    }

}
