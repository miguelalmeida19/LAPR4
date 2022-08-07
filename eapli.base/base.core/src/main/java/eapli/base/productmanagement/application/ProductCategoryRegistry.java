//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.framework.validations.Invariants;

public final class ProductCategoryRegistry {

    private static ProductCategoryManagementService productCategoryService;

    private ProductCategoryRegistry() {
    }

    public static void configure(final ProductCategoryRepository productCategoryRepo) {
        productCategoryService = new ProductCategoryManagementService(productCategoryRepo);
    }

    public static ProductCategoryManagementService productCategoryService() {
        Invariants.nonNull(productCategoryService);
        return productCategoryService;
    }
}