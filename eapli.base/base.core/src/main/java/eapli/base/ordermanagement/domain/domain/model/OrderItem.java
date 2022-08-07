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
package eapli.base.ordermanagement.domain.domain.model;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
public class OrderItem implements AggregateRoot<String> {

    @Version
    private Long version;

    @GenericGenerator(name = "seq_id", strategy = "eapli.base.productmanagement.domain.model.ProductIdGenerator")

    @Id @GeneratedValue(generator = "seq_id")
    private String orderItemId;
    private Integer quantity;
    private Double totalPrice;

    @OneToOne(fetch = FetchType.EAGER)
    @IndexColumn(name="productId")
    private Product productid;

    private boolean active;

    public OrderItem(final Integer quantity, final Double totalPrice, final Product product) {
        if (quantity == null || totalPrice == null || product == null) {
            throw new IllegalArgumentException();
        }
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productid = product;
    }

    protected OrderItem() {
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
        return this.orderItemId;
    }

    public Integer quantity() {
        return itemQuantity();
    }

    private Integer itemQuantity() {
        return this.quantity;
    }

    public Double totalPrice() {
        return itemTotalPrice();
    }

    private Double itemTotalPrice() {
        return this.totalPrice;
    }

    @Transactional
    public Product product() {
        return this.productid;
    }

    public boolean isActive() {
        return this.active;
    }
}
