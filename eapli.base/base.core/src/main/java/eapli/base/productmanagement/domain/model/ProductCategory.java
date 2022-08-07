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

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import javassist.runtime.Desc;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import java.util.List;

@Entity
public class ProductCategory implements AggregateRoot<Code> {

    @Version
    private Long version;

    @EmbeddedId
    private Code code;
    @Embedded
    private Description description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    private List<Product> products;

    private boolean active;

    public ProductCategory(final Code code, final Description description) {
        if (code == null || description == null) {
            throw new IllegalArgumentException();
        }
        this.code = code;
        this.description = description;
    }

    protected ProductCategory() {
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
    public Code identity() {
        return this.code;
    }

    public Description description() {
        return productCategoryDescription();
    }

    private Description productCategoryDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return description.toString();
    }

    public boolean isActive() {
        return this.active;
    }
}