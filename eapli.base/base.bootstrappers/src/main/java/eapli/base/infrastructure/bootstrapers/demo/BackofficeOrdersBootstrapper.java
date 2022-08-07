package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.agvmanagement.application.ListAgvController;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.infrastructure.bootstrapers.OrdersBootstrapperBase;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.domain.application.AssignAgvOrderController;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.PaymentMethod;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.application.ListProductsController;
import eapli.base.productmanagement.domain.model.Product;
import eapli.framework.actions.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackofficeOrdersBootstrapper extends OrdersBootstrapperBase implements Action {

    private final ListProductsController productsController = new ListProductsController();
    private final ListCustomersController customersController = new ListCustomersController();
    private final ListAgvController agvController = new ListAgvController();

    @Override
    public boolean execute(){
        List<Product> productList = (List<Product>) productsController.allProducts();
        OrderItem rolex_1 = registerOrderItem("1", productList.get(8));
        OrderItem rolex_2 = registerOrderItem("2", productList.get(8));

        OrderItem vallado_1 = registerOrderItem("1", productList.get(3));
        OrderItem vallado_2 = registerOrderItem("2", productList.get(3));
        OrderItem vallado_3 = registerOrderItem("3", productList.get(3));
        OrderItem vallado_4 = registerOrderItem("4", productList.get(3));
        OrderItem vallado_5 = registerOrderItem("5", productList.get(3));
        OrderItem vallado_6 = registerOrderItem("6", productList.get(3));

        OrderItem adidas_nmd_1 = registerOrderItem("1", productList.get(0));
        OrderItem adidas_nmd_2 = registerOrderItem("2", productList.get(0));

        OrderItem adidas_stan_1 = registerOrderItem("1", productList.get(1));
        OrderItem adidas_stan_2 = registerOrderItem("2", productList.get(1));

        OrderItem nike_1 = registerOrderItem("1", productList.get(2));
        OrderItem nike_2 = registerOrderItem("2", productList.get(2));

        OrderItem iphone13_1 = registerOrderItem("1", productList.get(4));
        OrderItem iphone13_2 = registerOrderItem("2", productList.get(4));
        OrderItem iphone13_3 = registerOrderItem("3", productList.get(4));

        OrderItem iphone13_max_1 = registerOrderItem("1", productList.get(5));
        OrderItem iphone13_max_2 = registerOrderItem("2", productList.get(5));
        OrderItem iphone13_max_3 = registerOrderItem("3", productList.get(5));

        OrderItem samsungA_1 = registerOrderItem("1", productList.get(6));
        OrderItem samsungA_2 = registerOrderItem("2", productList.get(6));

        OrderItem samsungflip_1 = registerOrderItem("1", productList.get(7));
        OrderItem samsungflip_2 = registerOrderItem("2", productList.get(7));

        OrderItem tissot_1 = registerOrderItem("1", productList.get(9));
        OrderItem tissot_2 = registerOrderItem("2", productList.get(9));

        OrderItem fifa22_1 = registerOrderItem("1", productList.get(10));
        OrderItem fifa22_2 = registerOrderItem("2", productList.get(10));
        OrderItem fifa22_3 = registerOrderItem("3", productList.get(10));
        OrderItem fifa22_4 = registerOrderItem("4", productList.get(10));

        OrderItem nba2k22_1 = registerOrderItem("1", productList.get(11));
        OrderItem nba2k22_2 = registerOrderItem("2", productList.get(11));
        OrderItem nba2k22_3 = registerOrderItem("3", productList.get(11));
        OrderItem nba2k22_4 = registerOrderItem("4", productList.get(11));

        OrderItem codV_1 = registerOrderItem("1", productList.get(12));
        OrderItem codV_2 = registerOrderItem("2", productList.get(12));
        OrderItem codV_3 = registerOrderItem("3", productList.get(12));
        OrderItem codV_4 = registerOrderItem("4", productList.get(12));

        OrderItem gta_1 = registerOrderItem("1", productList.get(13));
        OrderItem gta_2 = registerOrderItem("2", productList.get(13));
        OrderItem gta_3 = registerOrderItem("3", productList.get(13));
        OrderItem gta_4 = registerOrderItem("4", productList.get(13));

        List<OrderItem> orderItemList_1 = new ArrayList<>();
        orderItemList_1.add(tissot_2);

        List<OrderItem> orderItemList_2 = new ArrayList<>();
        orderItemList_2.add(0,rolex_1);
        orderItemList_2.add(1,fifa22_1);
        orderItemList_2.add(2,nike_1);
        orderItemList_2.add(3,iphone13_3);

        List<OrderItem> orderItemList_3 = new ArrayList<>();
        orderItemList_3.add(0,adidas_nmd_1);

        List<OrderItem> orderItemList_4 = new ArrayList<>();
        orderItemList_4.add(0,gta_1);
        orderItemList_4.add(1,codV_1);
        orderItemList_4.add(2,nba2k22_1);
        orderItemList_4.add(3,adidas_stan_1);

        List<OrderItem> orderItemList_5 = new ArrayList<>();
        orderItemList_5.add(0,iphone13_1);
        orderItemList_5.add(1,iphone13_2);

        List<OrderItem> orderItemList_6 = new ArrayList<>();
        orderItemList_6.add(0,iphone13_max_1);
        orderItemList_6.add(1,samsungflip_2);

        List<OrderItem> orderItemList_7 = new ArrayList<>();
        orderItemList_7.add(0,nike_2);

        List<OrderItem> orderItemList_8 = new ArrayList<>();
        orderItemList_8.add(0,samsungA_1);
        orderItemList_8.add(1,vallado_6);

        List<OrderItem> orderItemList_9 = new ArrayList<>();
        orderItemList_9.add(0,iphone13_max_1);

        List<Customer> customerList = (List<Customer>) customersController.allCustomers();
        System.out.println("REGISTERING ORDERS!!!");
        ProductOrder p01 =  registerOrder("green", PaymentMethod.PAYPAL.toString(), orderItemList_1, customerList.get(1));
        ProductOrder p02 = registerOrder("blue", PaymentMethod.AMAZON_PAY.toString(), orderItemList_2, customerList.get(0));
        ProductOrder p03 = registerOrder("green", PaymentMethod.BANK_TRANSFER.toString(), orderItemList_3, customerList.get(6));
        ProductOrder p04 = registerOrder("standard", PaymentMethod.GOOGLE_PAY.toString(), orderItemList_4, customerList.get(3));
        ProductOrder p05 = registerOrder("standard", PaymentMethod.DIGITAL_CURRENCIES.toString(), orderItemList_5, customerList.get(2));
        ProductOrder p06 = registerOrder("green", PaymentMethod.DIRECT_DEBIT_PAYMENT.toString(), orderItemList_6, customerList.get(4));
        ProductOrder p07 = registerOrder("standard", PaymentMethod.APPLE_PAY.toString(), orderItemList_7, customerList.get(5));
        ProductOrder p08 = registerOrder("standard", PaymentMethod.GIFT_CARD.toString(), orderItemList_8, customerList.get(7));

        /**
         List<AGV> AGVList = (List<AGV>) agvController.allAgvs();
         System.out.println("FORCING ORDERS!!!");
         AssignAgvOrderController assignAgvOrderController = new AssignAgvOrderController();
         try {
         assignAgvOrderController.setAgvOrder(p01);
         } catch (IOException e) {
         e.printStackTrace();
         }
         try {
         assignAgvOrderController.setAgvOrder(p04);
         } catch (IOException e) {
         e.printStackTrace();
         }

         for (AGV AGV : AGVList){
         PersistenceContext.repositories().agvs().save(AGV);
         }
         */


        return true;
    }
}
