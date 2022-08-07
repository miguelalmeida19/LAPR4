/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.utils.CountryCurrency;
import eapli.base.ordermanagement.domain.utils.CountryTaxes;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@UseCaseController
public class AddOrderController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrderManagementService orderSvc = OrderRegistry.orderService();
    private final CountryCurrency countryCurrency = new CountryCurrency();

    public ProductOrder addOrder(final String shippingMethod, final String paymentMethod, final List<OrderItem> orderItems, final Customer customer) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        return orderSvc.addNewOrder(shippingMethod, paymentMethod, orderItems, customer);
    }

    public Double getTotalOrderPriceWithoutTax(final List<OrderItem> orderItems, final String country){
        Stream<OrderItem> notNullObjs =
                orderItems.stream().filter(obj -> obj.totalPrice() != null);

        Double priceWithoutConverted = notNullObjs.mapToDouble(OrderItem::totalPrice).sum();

        //return CountryCurrency.convert(country, priceWithoutConverted);
        return priceWithoutConverted;
    }

    public Double getTotalOrderPriceWithTax(final List<OrderItem> orderItems, final String country){
        CountryTaxes countryTaxes = new CountryTaxes();
        Double priceWithoutConverted = countryTaxes.calculatePrice(country, getTotalOrderPriceWithoutTax(orderItems, country));
        //return CountryCurrency.convert(country, priceWithoutConverted);
        return priceWithoutConverted;
    }
}
