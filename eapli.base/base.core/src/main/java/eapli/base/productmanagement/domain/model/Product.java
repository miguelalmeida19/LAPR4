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
package eapli.base.productmanagement.domain.model;

import javax.persistence.*;

import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

@Entity
public class Product implements AggregateRoot<String> {

    @Version
    private Long version;

    @GenericGenerator(name = "seq_id", strategy = "eapli.base.productmanagement.domain.model.ProductIdGenerator")

    @Id @GeneratedValue(generator = "seq_id")
    private String productid;
    @Embedded
    private Price price;
    @Embedded
    private Brand brand;
    @Embedded
    private Reference reference;
    @Embedded
    private Shortdescription shortDescription;
    @Embedded
    private Longdescription longDescription;
    @Embedded
    private Technicaldescription technicalDescription;
    @Embedded
    private Barcode barcode;
    @Embedded @Nullable
    private ProductionCode productionCode;
    private Double width;
    private Double length;
    private Double height;
    private Double weight;
    @Lob
    private byte[] photo;
    @Embedded
    private AisleId aisles;
    @Embedded
    private RowsId row;
    private Long shelfId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryCode")
    private ProductCategory productCategory;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderItemId")
    private OrderItem orderItem;

    private boolean active;

    @Transactional
    public RowsId getRow() {
        return row;
    }

    public AisleId getAisles() {
        return aisles;
    }

    public Product(final Price price, final Brand brand, final Reference reference, final Shortdescription shortDescription, final Longdescription longDescription,
                   final Technicaldescription technicalDescription, final Barcode barcode, final ProductionCode productionCode, final Double width,
                   final Double length, final Double height, final Double weight, final ProductCategory productCategory, final byte[] photo,
                   final AisleId aisleId, final RowsId rowsId, final Long shelf) {
        if (price == null || brand == null || reference == null || shortDescription == null || longDescription == null || technicalDescription == null || barcode == null
        || length == null || height == null || weight == null || productCategory == null) {
            throw new IllegalArgumentException();
        }
        this.price = price;
        this.brand = brand;
        this.reference = reference;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.technicalDescription = technicalDescription;
        this.barcode = barcode;
        this.productionCode = productionCode;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.productCategory = productCategory;
        this.photo = photo;
        this.aisles = aisleId;
        this.row = rowsId;
        this.shelfId = shelf;
    }

    protected Product() {
        // for ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.productid;
    }

    public Price price() {
        return productPrice();
    }

    private Price productPrice() {
        return this.price;
    }

    public Brand brand() {
        return productBrand();
    }

    private Brand productBrand() {
        return this.brand;
    }

    public Reference reference() {
        return productReference();
    }

    private Reference productReference() {
        return this.reference;
    }

    public Shortdescription shortDescription() {
        return productShortDescription();
    }

    private Shortdescription productShortDescription() {
        return this.shortDescription;
    }

    public Longdescription longDescription() {
        return productLongDescription();
    }

    private Longdescription productLongDescription() {
        return this.longDescription;
    }

    public Technicaldescription technicalDescription() {
        return productTechnicalDescription();
    }

    private Technicaldescription productTechnicalDescription() {
        return this.technicalDescription;
    }

    public ProductionCode productionCode() {
        return productProductionCode();
    }

    private ProductionCode productProductionCode() {
        return this.productionCode;
    }

    public Barcode barcode() {
        return productBarcode();
    }

    private Barcode productBarcode() {
        return this.barcode;
    }

    public Double width() {
        return productWidth();
    }

    private Double productWidth() {
        return this.width;
    }

    public Double length() {
        return productLength();
    }

    private Double productLength() {
        return this.length;
    }

    public Double height() {
        return productHeight();
    }

    private Double productHeight() {
        return this.height;
    }

    @Transactional
    public Double productWeight() {
        return this.weight;
    }

    public ProductCategory category() {
        return productCategory();
    }

    public String prodCat() {
        return productCategory.description().toString();
    }

    private ProductCategory productCategory() {
        return this.productCategory;
    }

    public byte[] photo() {
        return productPhoto();
    }

    private byte[] productPhoto() {
        return this.photo;
    }

    public AisleId aisles() {
        return productAisles();
    }

    private AisleId productAisles() {
        return this.aisles;
    }

    public RowsId rowsId() {
        return productRowsId();
    }

    private RowsId productRowsId() {
        return this.row;
    }

    public Long shelfId() {
        return productShelfId();
    }

    private Long productShelfId() {
        return this.shelfId;
    }

    public void setAisles(AisleId aisles) {
        this.aisles = aisles;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setLongDescription(Longdescription longDescription) {
        this.longDescription = longDescription;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public void setProductionCode(@Nullable ProductionCode productionCode) {
        this.productionCode = productionCode;
    }

    public void setShortDescription(Shortdescription shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setTechnicalDescription(Technicaldescription technicalDescription) {
        this.technicalDescription = technicalDescription;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setRow(RowsId row) {
        this.row = row;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }
}