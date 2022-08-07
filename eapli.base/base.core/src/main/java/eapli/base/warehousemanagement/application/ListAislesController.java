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
package eapli.base.warehousemanagement.application;

import eapli.base.productmanagement.application.ProductManagementService;
import eapli.base.productmanagement.application.ProductRegistry;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@UseCaseController
public class ListAislesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AislesManagementService aislesManagementService = AislesRegistry.aislesManagementService();

    public Iterable<Aisles> allAisles() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        return aislesManagementService.allAisles();
    }

    public Optional<Aisles> find(final AisleId u) {
        return aislesManagementService.aislesOfIdentity(u);
    }
}
