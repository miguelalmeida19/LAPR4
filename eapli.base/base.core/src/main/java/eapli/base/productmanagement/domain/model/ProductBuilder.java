//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.productmanagement.domain.model;

import eapli.base.productmanagement.domain.model.*;
import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.base.warehousemanagement.domain.model.Shelf;
import eapli.framework.domain.model.DomainFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Embedded;
import java.util.regex.Pattern;

/*
Colocar acceptance criteria aqui!
 */

public class ProductBuilder implements DomainFactory<Product> {

    private final int SHORT_DESCRIPTION_MIN = 4;
    private final int SHORT_DESCRIPTION_MAX = 20;
    private final int LONG_DESCRIPTION_MIN = 10;
    private final int LONG_DESCRIPTION_MAX = 100;
    private final int TECHNICAL_DESCRIPTION_MIN = 10;
    private final int TECHNICAL_DESCRIPTION_MAX = 60;

    private static final Logger LOGGER = LogManager.getLogger(ProductBuilder.class);
    private String productid;
    private Price price;
    private Brand brand;
    private Reference reference;
    private Shortdescription shortdescription;
    private Longdescription longdescription;
    private Technicaldescription technicaldescription;
    private Barcode barcode;
    private ProductionCode productionCode;
    private Double width;
    private Double length;
    private Double height;
    private Double weight;
    private ProductCategory productCategory;
    private byte[] photo;
    private AisleId aisleId;
    private RowsId rowsId;
    private Long shelf;

    public ProductBuilder() {
    }

    public ProductBuilder with(final String price, final String brand, final String reference, final String shortdescription,
                               final String longdescription, final String techinicaldescription, final String barcode,
                               final String width, final String length, final String height, final String weight,
                               final ProductCategory productCategory, final byte[] photo, final AisleId aisleId, final RowsId rowsId, final Long shelf) {
        this.withPrice(price);
        this.withBrand(brand);
        this.withReference(reference);
        this.withShortDescription(shortdescription);
        this.withLongDescription(longdescription);
        this.withTechnicalDescription(techinicaldescription);
        this.withBarcode(barcode);
        this.withProductionCode();
        this.withWidth(width);
        this.withLength(length);
        this.withHeight(height);
        this.withWeight(weight);
        this.productCategory = productCategory;
        this.photo = photo;
        this.aisleId = aisleId;
        this.rowsId = rowsId;
        this.shelf = shelf;

        return this;
    }

    public ProductBuilder withPrice(final String price) {
        try {
            if (Double.parseDouble(price) > 0) {
                this.price = Price.valueOf(price);
                return this;
            } else {
                throw new IllegalArgumentException("Price must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Price must be >0!");
        }
    }

    public ProductBuilder withBrand(final String brand) {
        try{
            Integer.parseInt(brand);
            throw new IllegalArgumentException("Brand name is invalid.");
        }catch (Exception e){
            this.brand = Brand.valueOf(brand);
            return this;
        }
    }

    public ProductBuilder withReference(final String reference) {
        try {
            if (Integer.parseInt(reference) > 0) {
                this.reference = Reference.valueOf(reference);
                return this;
            } else {
                throw new IllegalArgumentException("Reference must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Reference must be >0!");
        }
    }

    public ProductBuilder withShortDescription(final String shortDescription) {
        if (shortDescription.length()>=SHORT_DESCRIPTION_MIN && shortDescription.length()<=SHORT_DESCRIPTION_MAX){
            this.shortdescription = Shortdescription.valueOf(shortDescription);
            return this;
        }else{
            throw new IllegalArgumentException(String.format("Short Description must contain at least %d and up to %d characters", SHORT_DESCRIPTION_MIN, SHORT_DESCRIPTION_MAX));
        }
    }

    public ProductBuilder withLongDescription(final String longDescription) {
        if (longDescription.length()>=LONG_DESCRIPTION_MIN && longDescription.length()<=LONG_DESCRIPTION_MAX){
            this.longdescription = Longdescription.valueOf(longDescription);
            return this;
        }else{
            throw new IllegalArgumentException(String.format("Long Description must contain at least %d and up to %d characters", LONG_DESCRIPTION_MIN, LONG_DESCRIPTION_MAX));
        }
    }

    public ProductBuilder withTechnicalDescription(final String technicalDescription) {
        if (technicalDescription.length()>=TECHNICAL_DESCRIPTION_MIN && technicalDescription.length()<=TECHNICAL_DESCRIPTION_MAX){
            this.technicaldescription = Technicaldescription.valueOf(technicalDescription);
            return this;
        }else{
            throw new IllegalArgumentException(String.format("Technical Description must contain at least %d and up to %d characters", TECHNICAL_DESCRIPTION_MIN, TECHNICAL_DESCRIPTION_MAX));
        }
    }

    public ProductBuilder withBarcode(final String barcode) {
        this.barcode = Barcode.valueOf(barcode);
        return this;
    }

    public ProductBuilder withProductionCode() {
        ProductIdGenerator productIdGenerator = new ProductIdGenerator();
        this.productionCode = ProductionCode.valueOf(productIdGenerator.generateProductId());
        return this;
    }

    public ProductBuilder withWidth(final String width) {
        try {
            if (Double.parseDouble(width) > 0) {
                this.width = Double.valueOf(width);
                return this;
            } else {
                throw new IllegalArgumentException("Width must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Width must be >0!");
        }
    }

    public ProductBuilder withLength(final String length) {
        try {
            if (Double.parseDouble(length) > 0) {
                this.length = Double.valueOf(length);
                return this;
            } else {
                throw new IllegalArgumentException("Length must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Length must be >0!");
        }
    }

    public ProductBuilder withHeight(final String height) {
        try {
            if (Double.parseDouble(height) > 0) {
                this.height = Double.valueOf(height);
                return this;
            } else {
                throw new IllegalArgumentException("Height must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Height must be >0!");
        }
    }

    public ProductBuilder withWeight(final String weight) {
        try {
            if (Double.parseDouble(weight) > 0) {
                this.weight = Double.valueOf(weight);
                return this;
            } else {
                throw new IllegalArgumentException("Weight must be >0!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Weight must be >0!");
        }
    }

    public Product build() {
        Product product;
        product = new Product(this.price, this.brand, this.reference, this.shortdescription, this.longdescription,
                this.technicaldescription, this.barcode, this.productionCode, this.width, this.length, this.height,
                this.weight, this.productCategory, this.photo, this.aisleId, this.rowsId, this.shelf);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new product [{}]", this.productid);
        }

        return product;
    }
}

