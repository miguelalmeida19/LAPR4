//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.application;

import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.validations.Invariants;

public final class ProductRegistry {

    private static ProductManagementService productService;

    private ProductRegistry() {
    }

    public static void configure(final ProductRepository productRepo) {
        productService = new ProductManagementService(productRepo);
    }

    public static ProductManagementService productService() {
        Invariants.nonNull(productService);
        return productService;
    }
}