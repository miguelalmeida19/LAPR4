package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Reference implements ValueObject, Comparable<Reference> {

    private static final long serialVersionUID = 1L;

    private String reference;

    public Reference(final String Reference) {
        if (StringPredicates.isNullOrEmpty(Reference)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.reference = Reference;
    }

    protected Reference() {
        // for ORM
    }

    public static Reference valueOf(final String Reference) {
        return new Reference(Reference);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reference)) {
            return false;
        }

        final Reference that = (Reference) o;
        return this.reference.equals(that.reference);
    }

    @Override
    public int hashCode() {
        return this.reference.hashCode();
    }

    @Override
    public String toString() {
        return this.reference;
    }

    @Override
    public int compareTo(final Reference arg0) {
        return reference.compareTo(arg0.reference);
    }
}