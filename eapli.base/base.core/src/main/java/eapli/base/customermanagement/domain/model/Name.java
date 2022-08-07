package eapli.base.customermanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import java.util.Objects;

@Embeddable
public class Name implements ValueObject, Comparable<Name> {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    public Name(final String firstName, final String lastName) {
        if (StringPredicates.isNullOrEmpty(firstName) || StringPredicates.isNullOrEmpty(lastName) ) {
            throw new IllegalArgumentException(
                    "Name should neither be null nor empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected Name() {
        // for ORM
    }

    public static Name valueOf(final String firstName, final String lastName) {
        return new Name(firstName, lastName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Name that = (Name) o;
        return (this.firstName.equals(that.firstName) && this.lastName.equals(that.lastName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public int compareTo(final Name arg0) {
        if (firstName.compareTo(arg0.firstName)==0){
            return lastName.compareTo(arg0.lastName);
        }else {
            return firstName.compareTo(arg0.firstName);
        }
    }
}