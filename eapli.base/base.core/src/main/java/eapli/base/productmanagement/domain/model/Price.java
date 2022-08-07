package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Price implements ValueObject, Comparable<Price> {

    private static final long serialVersionUID = 1L;

    private String price;

    public Price(final String Price) {
        if (StringPredicates.isNullOrEmpty(Price)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.price = Price;
    }

    protected Price() {
        // for ORM
    }

    public static Price valueOf(final String Price) {
        return new Price(Price);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }

        final Price that = (Price) o;
        return this.price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return this.price.hashCode();
    }

    @Override
    public String toString() {
        return this.price;
    }

    @Override
    public int compareTo(final Price arg0) {
        return price.compareTo(arg0.price);
    }
}