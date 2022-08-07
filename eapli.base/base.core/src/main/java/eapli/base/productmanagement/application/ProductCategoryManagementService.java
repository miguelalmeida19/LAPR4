//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.application;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.CustomerId;
import eapli.base.customermanagement.domain.model.CustomerBuilder;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.productmanagement.domain.model.*;
import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductCategoryManagementService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryManagementService(final ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Transactional
    public ProductCategory addNewProductCategory(final String code, final String description) {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        productCategoryBuilder.with(code, description);
        ProductCategory newProductCategory = productCategoryBuilder.build();
        return this.productCategoryRepository.save(newProductCategory);
    }

    public Iterable<ProductCategory> activeProductCategories() {
        return this.productCategoryRepository.findByActive(true);
    }

    public Iterable<ProductCategory> deactivatedProductCategories() {
        return this.productCategoryRepository.findByActive(false);
    }

    public Iterable<ProductCategory> allProductCategories() {
        return this.productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> productCategoryOfIdentity(final Code code) {
        return this.productCategoryRepository.ofIdentity(code);
    }
}
