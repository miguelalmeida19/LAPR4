package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Shortdescription implements ValueObject, Comparable<Shortdescription> {

    private static final long serialVersionUID = 1L;

    private String shortdescription;

    public Shortdescription(final String Shortdescription) {
        if (StringPredicates.isNullOrEmpty(Shortdescription)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.shortdescription = Shortdescription;
    }

    protected Shortdescription() {
        // for ORM
    }

    public static Shortdescription valueOf(final String Shortdescription) {
        return new Shortdescription(Shortdescription);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shortdescription)) {
            return false;
        }

        final Shortdescription that = (Shortdescription) o;
        return this.shortdescription.equals(that.shortdescription);
    }

    @Override
    public int hashCode() {
        return this.shortdescription.hashCode();
    }

    @Override
    public String toString() {
        return this.shortdescription;
    }

    @Override
    public int compareTo(final Shortdescription arg0) {
        return shortdescription.compareTo(arg0.shortdescription);
    }
}