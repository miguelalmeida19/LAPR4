package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Technicaldescription implements ValueObject, Comparable<Technicaldescription> {

    private static final long serialVersionUID = 1L;

    private String technicaldescription;

    public Technicaldescription(final String Technicaldescription) {
        if (StringPredicates.isNullOrEmpty(Technicaldescription)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.technicaldescription = Technicaldescription;
    }

    protected Technicaldescription() {
        // for ORM
    }

    public static Technicaldescription valueOf(final String Technicaldescription) {
        return new Technicaldescription(Technicaldescription);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Technicaldescription)) {
            return false;
        }

        final Technicaldescription that = (Technicaldescription) o;
        return this.technicaldescription.equals(that.technicaldescription);
    }

    @Override
    public int hashCode() {
        return this.technicaldescription.hashCode();
    }

    @Override
    public String toString() {
        return this.technicaldescription;
    }

    @Override
    public int compareTo(final Technicaldescription arg0) {
        return technicaldescription.compareTo(arg0.technicaldescription);
    }
}