//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.domain.model;

import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Colocar acceptance criteria aqui!
 */

public class ProductCategoryBuilder implements DomainFactory<ProductCategory> {
    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryBuilder.class);
    private Code code;
    private Description description;

    public ProductCategoryBuilder() {
    }

    public ProductCategoryBuilder with(final String code, final String description) {
        this.withCode(code);
        this.withDescription(description);

        return this;
    }

    public ProductCategoryBuilder withCode(final String code) {
        this.code = Code.valueOf(code);
        return this;
    }

    public ProductCategoryBuilder withDescription(final String description) {
        this.description = Description.valueOf(description);
        return this;
    }



    public ProductCategory build() {
        ProductCategory productCategory;
        productCategory = new ProductCategory(this.code, this.description);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new product [{}]", this.code);
        }

        return productCategory;
    }
}

