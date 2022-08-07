package eapli.base.agvmanagement.domain.model;

import eapli.base.productmanagement.domain.model.Description;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AGVShortDescription implements ValueObject, Comparable<AGVShortDescription>  {

    private static final long serialVersionUID = 1L;

    private String description;

    public AGVShortDescription(final String description) {
        if (StringPredicates.isNullOrEmpty(description)) {
            throw new IllegalArgumentException(
                    "AGV short description should neither be null nor empty");
        }
        this.description = description;
    }

    protected AGVShortDescription() {
        // for ORM
    }

    public static AGVShortDescription valueOf(final String description) {
        return new AGVShortDescription(description);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVShortDescription)) {
            return false;
        }

        final AGVShortDescription that = (AGVShortDescription) o;
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
    public int compareTo(final AGVShortDescription arg0) {
        return description.compareTo(arg0.description);
    }

    public int length() {
        return description.length();
    }
}
