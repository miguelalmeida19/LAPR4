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
package eapli.base.productmanagement.application;

import java.util.Optional;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.CustomerId;
import eapli.base.productmanagement.application.ProductManagementService;
import eapli.base.productmanagement.application.ProductRegistry;
import eapli.base.productmanagement.domain.model.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ListProductsController{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductManagementService productSvc = ProductRegistry.productService();

    public Iterable<Product> allProducts() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.allProducts();
    }

    public Iterable<Product> findByProductCategory(ProductCategory productCategory){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByProductCategory(productCategory);
    }

    public Iterable<Product> findByShortDescription(Shortdescription shortdescription){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByShortDescription(shortdescription);
    }

    public Iterable<Product> findByTechnicalDescription(Technicaldescription technicaldescription){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByTechnicalDescription(technicaldescription);
    }

    public Iterable<Product> findByLongDescription(Longdescription longdescription){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByLongDescription(longdescription);
    }

    public Iterable<Product> findByBrand(Brand brand){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByBrand(brand);
    }

    public Iterable<Product> findByBrandAndProductCategory(Brand brand, ProductCategory category){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.CLIENT_USER);

        return productSvc.findByBrandAndProductCategory(brand, category);
    }

    public Optional<Product> find(final String u) {
        return productSvc.productOfIdentity(u);
    }
}
