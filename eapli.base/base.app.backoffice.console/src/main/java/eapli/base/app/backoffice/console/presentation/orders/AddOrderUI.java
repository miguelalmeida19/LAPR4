/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.app.backoffice.console.presentation.customers.CustomerPrinter;
import eapli.base.app.backoffice.console.presentation.products.ListProductsFiltersUI;
import eapli.base.app.backoffice.console.presentation.products.ProductCatalogUI;
import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.application.AddOrderController;
import eapli.base.ordermanagement.domain.domain.application.AddOrderItemController;
import eapli.base.ordermanagement.domain.domain.application.ListOrdersController;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.PaymentMethod;
import eapli.base.productmanagement.application.ListProductsController;
import eapli.base.productmanagement.domain.model.Product;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;


public class AddOrderUI extends AbstractUI {

    private final AddOrderController theController = new AddOrderController();
    private final ListProductsController listProductsController = new ListProductsController();

    private final ListCustomersController listCustomersController = new ListCustomersController();

    private final ListOrdersController listOrdersController = new ListOrdersController();

    private final AddOrderItemController addOrderItemController = new AddOrderItemController();

    private List<OrderItem> orderItems = new ArrayList<>();

    private boolean selectMore = true;

    @Override
    protected boolean doShow() {
        final Iterable<Customer> customers = listCustomersController.allCustomers();
        final SelectWidget<Customer> selector1 = new SelectWidget<>("Select a Customer", customers, new CustomerPrinter());
        selector1.show();
        final Customer customer = selector1.selectedElement();
        while (selectMore){
            String option = "";
            while (!option.equals("1") && !option.equals("2") && !option.equals("0")){
                option = Console.readLine("Do you want add by product id or select it?\n[1] Product ID\n[2] Select Product from Catalog\n[0] Exit");
            }
            if (option.equals("1")){
                String productId = Console.readLine("Type product id");
                final Iterable<Product> products = listProductsController.allProducts();
                Product selected = null;
                for (Product product: products){
                    if (product.identity().equals(productId)){
                        selected = product;
                    }
                }
                if (selected!=null){
                    final String quantity = Console.readLine("Quantity");
                    OrderItem orderItem = this.addOrderItemController.addOrderItem(quantity, selected);
                    orderItems.add(orderItem);
                }
                else {
                    throw new IllegalArgumentException("Product Id not found!");
                }
            } else if(option.equals("2")) {

                ProductCatalogUI productCatalogUI = new ProductCatalogUI();
                ListProductsFiltersUI listProductsFiltersUI = productCatalogUI.showCatalog();
                listProductsFiltersUI.show();
                final Iterable<Product> products = listProductsFiltersUI.elementsList();
                final SelectWidget<Product> selector = new SelectWidget<>("Select a Product", products, new ProductPrinter());
                selector.show();
                final Product product = selector.selectedElement();

                if (product!=null){
                    final String quantity = Console.readLine("Quantity");
                    OrderItem orderItem = this.addOrderItemController.addOrderItem(quantity, product);
                    orderItems.add(orderItem);
                }else {
                    selectMore = false;
                }
            }else {
                selectMore=false;
            }
        }

        final String shippingMethod = Console.readLine("Shipping Method");

        final Iterable<PaymentMethod> paymentMethods = List.of(PaymentMethod.values());
        final SelectWidget<PaymentMethod> selector2 = new SelectWidget<>("Payment Method", paymentMethods, new PaymentMethodPrinter());
        selector2.show();

        final String paymentMethod = selector2.selectedElement().getPaymentMethod();
        final String country = customer.addresses().toString().split("___")[4];

        new ListOrderItemsForOrderUI(country, orderItems).show();
        System.out.printf("%30s%30s", "ORDER PRICE WITHOUT TAXES", "ORDER PRICE WITH TAXES");
        System.out.printf("%n%30s%30s%n%n", theController.getTotalOrderPriceWithoutTax(orderItems, country), theController.getTotalOrderPriceWithTax(orderItems, country));

        try {
            this.theController.addOrder(shippingMethod, paymentMethod, orderItems, customer);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That order id is already in use.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Add ProductOrder";
    }
}
