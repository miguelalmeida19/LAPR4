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
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Vat implements ValueObject, Comparable<Vat> {

    private static final long serialVersionUID = 1L;

    private String vat;

    public Vat(final String Vat) {
        if (StringPredicates.isNullOrEmpty(Vat)) {
            throw new IllegalArgumentException(
                    "Mecanographic id should neither be null nor empty");
        }
        this.vat = Vat;
    }

    protected Vat() {
        // for ORM
    }

    public static Vat valueOf(final String Vat) {
        return new Vat(Vat);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vat)) {
            return false;
        }

        final Vat that = (Vat) o;
        return this.vat.equals(that.vat);
    }

    @Override
    public int hashCode() {
        return this.vat.hashCode();
    }

    @Override
    public String toString() {
        return this.vat;
    }

    @Override
    public int compareTo(final Vat arg0) {
        return vat.compareTo(arg0.vat);
    }
}
