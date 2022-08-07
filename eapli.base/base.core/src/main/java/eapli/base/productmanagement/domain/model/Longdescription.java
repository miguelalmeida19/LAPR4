package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Longdescription implements ValueObject, Comparable<Longdescription> {

    private static final long serialVersionUID = 1L;

    private String longdescription;

    public Longdescription(final String Longdescription) {
        if (StringPredicates.isNullOrEmpty(Longdescription)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.longdescription = Longdescription;
    }

    protected Longdescription() {
        // for ORM
    }

    public static Longdescription valueOf(final String Longdescription) {
        return new Longdescription(Longdescription);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Longdescription)) {
            return false;
        }

        final Longdescription that = (Longdescription) o;
        return this.longdescription.equals(that.longdescription);
    }

    @Override
    public int hashCode() {
        return this.longdescription.hashCode();
    }

    @Override
    public String toString() {
        return this.longdescription;
    }

    @Override
    public int compareTo(final Longdescription arg0) {
        return longdescription.compareTo(arg0.longdescription);
    }
}