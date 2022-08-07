package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Brand implements ValueObject, Comparable<Brand> {

    private static final long serialVersionUID = 1L;

    private String brand;

    public Brand(final String Brand) {
        if (StringPredicates.isNullOrEmpty(Brand)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.brand = Brand;
    }

    protected Brand() {
        // for ORM
    }

    public static Brand valueOf(final String Brand) {
        return new Brand(Brand);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }

        final Brand that = (Brand) o;
        return this.brand.equals(that.brand);
    }

    @Override
    public int hashCode() {
        return this.brand.hashCode();
    }

    @Override
    public String toString() {
        return this.brand;
    }

    @Override
    public int compareTo(final Brand arg0) {
        return brand.compareTo(arg0.brand);
    }
}