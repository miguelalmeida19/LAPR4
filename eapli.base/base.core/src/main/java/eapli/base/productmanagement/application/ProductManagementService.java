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
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.RowsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductManagementService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductManagementService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addNewProduct(final String price, final String brand, final String reference, final String shortDescription,
                                 final String longDescription, final String techinicalDescription, final String barcode,
                                 final String width, final String length, final String height, final String weight,
                                 final ProductCategory category, final byte[] photo, final AisleId aisleId, final RowsId rowsId, final Long shelf) {
        ProductBuilder productBuilder = new ProductBuilder();
        productBuilder.with(price, brand, reference, shortDescription, longDescription, techinicalDescription, barcode, width, length, height, weight, category, photo, aisleId, rowsId, shelf);
        Product newProduct = productBuilder.build();
        return this.productRepository.save(newProduct);
    }

    public Iterable<Product> activeProducts() {
        return this.productRepository.findByActive(true);
    }

    public Iterable<Product> deactivatedProducts() {
        return this.productRepository.findByActive(false);
    }

    public Iterable<Product> allProducts() {
        return this.productRepository.findAll();
    }

    public Iterable<Product> findByProductCategory(ProductCategory productCategory){
        return this.productRepository.findByProductCategory(productCategory);
    }

    public Iterable<Product> findByShortDescription(Shortdescription shortdescription){
        return this.productRepository.findByShortDescription(shortdescription);
    }

    public Iterable<Product> findByTechnicalDescription(Technicaldescription technicaldescription){
        return this.productRepository.findByTechnicalDescription(technicaldescription);
    }

    public Iterable<Product> findByLongDescription(Longdescription longdescription){
        return this.productRepository.findByLongDescription(longdescription);
    }

    public Iterable<Product> findByBrand(Brand brand){
        return this.productRepository.findByBrand(brand);
    }

    public Iterable<Product> findByBrandAndProductCategory(Brand brand, ProductCategory category){
        return this.productRepository.findByBrandAndProductCategory(brand, category);
    }

    public Optional<Product> productOfIdentity(final String id) {
        return this.productRepository.ofIdentity(id);
    }
}
