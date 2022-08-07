package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Description implements ValueObject, Comparable<Description> {

    private static final long serialVersionUID = 1L;

    private String description;

    public Description(final String description) {
        if (StringPredicates.isNullOrEmpty(description)) {
            throw new IllegalArgumentException(
                    "Category description should neither be null nor empty");
        }
        this.description = description;
    }

    protected Description() {
        // for ORM
    }

    public static Description valueOf(final String description) {
        return new Description(description);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Description)) {
            return false;
        }

        final Description that = (Description) o;
        return this.description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int compareTo(final Description arg0) {
        return description.compareTo(arg0.description);
    }
}