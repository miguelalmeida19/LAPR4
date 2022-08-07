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
package eapli.base.customermanagement.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class CustomerId implements ValueObject, Comparable<CustomerId> {
    @Column(
            name = "customerId"
    )
    private static final long serialVersionUID = 1L;

    private String customerId;

    public CustomerId(final String customerId) {
        if (StringPredicates.isNullOrEmpty(customerId)) {
            throw new IllegalArgumentException(
                    "Customer id should neither be null nor empty");
        }
        this.customerId = customerId;
    }

    protected CustomerId() {
        // for ORM
    }

    public static CustomerId valueOf(final String customerId) {
        return new CustomerId(customerId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerId)) {
            return false;
        }

        final CustomerId that = (CustomerId) o;
        return this.customerId.equals(that.customerId);
    }

    @Override
    public int hashCode() {
        return this.customerId.hashCode();
    }

    @Override
    public String toString() {
        return this.customerId;
    }

    @Override
    public int compareTo(final CustomerId arg0) {
        return customerId.compareTo(arg0.customerId);
    }
}
