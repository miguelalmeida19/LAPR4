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
package eapli.base.productmanagement.application;

import eapli.base.customermanagement.application.CustomerManagementService;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@UseCaseController
public class AddProductController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductManagementService productSvc = ProductRegistry.productService();

    public Product addProduct(final String price, final String brand, final String reference, final String shortDescription,
                              final String longDescription, final String technicalDescription, final String barcode,
                              final String width, final String length, final String height, final String weight,
                              final ProductCategory productCategory, final File photo, final AisleId aisleId, final RowsId rowsId, final Long shelf) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);
        byte[] picInBytes = new byte[(int) photo.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(photo);
            fileInputStream.read(picInBytes);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productSvc.addNewProduct(price, brand, reference, shortDescription, longDescription, technicalDescription, barcode, width, length, height, weight, productCategory, picInBytes, aisleId, rowsId, shelf);
    }
}
