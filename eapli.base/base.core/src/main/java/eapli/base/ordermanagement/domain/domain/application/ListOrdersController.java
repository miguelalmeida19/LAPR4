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
package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.ordermanagement.domain.domain.model.AGVStatus;
import eapli.base.ordermanagement.domain.domain.model.OrderStatus;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UseCaseController
public class ListOrdersController{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrderManagementService orderService = OrderRegistry.orderService();

    public Iterable<ProductOrder> allOrders() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        return orderService.allOrders();
    }

    public Optional<ProductOrder> find(final String u) {
        return orderService.orderOfIdentity(u);
    }

    public Iterable<ProductOrder> findByAgvStatus(AGVStatus agvStatus){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);

        return orderService.findByAgvStatus(agvStatus);
    }

    public Iterable<ProductOrder> findByOrderStatus(OrderStatus orderStatus){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK,BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);

        return orderService.findByOrderStatus(orderStatus);
    }

    public Iterable<ProductOrder> findByAgv(AGV AGV){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);

        Iterable<ProductOrder> result = orderService.findByAgv(AGV);
        List<ProductOrder> filtered = new ArrayList<>();

        for (ProductOrder p : result){
            if (p.orderStatus() != OrderStatus.SHIPPED){
                filtered.add(p);
            }
        }

        return filtered;
    }
}
