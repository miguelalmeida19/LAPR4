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

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;

import java.util.List;


@Embeddable
public class Addresses implements ValueObject, Comparable<Addresses> {

    private static final long serialVersionUID = 1L;

    private String addresses;

    public Addresses(String addresses) {
        this.addresses = addresses;
    }

    protected Addresses() {
        // for ORM
    }

    public static Addresses valueOf(String addresses) {
        return new Addresses(addresses);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof String)) {
            return false;
        }

        final Addresses that = (Addresses) o;
        return this.addresses.equals(that.addresses);
    }

    @Override
    public int hashCode() {
        return this.addresses.hashCode();
    }

    @Override
    public String toString() {
        return addresses;
    }

    @Override
    public int compareTo(final Addresses arg0) {
        if(addresses.equals(arg0.addresses)){
            return 1;
        }
        if(!addresses.equals(arg0.addresses)){
            return 0;
        }else {
            return -1;
        }
    }
}
