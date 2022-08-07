package eapli.base.customermanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;

@Embeddable
public class Gender implements ValueObject, Comparable<Gender> {

    private static final long serialVersionUID = 1L;

    private String gender;

    public Gender(final String gender) {
        this.gender = gender;
    }

    protected Gender() {
        // for ORM
    }

    public static Gender valueOf(final String gender) {
        return new Gender(gender);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gender)) {
            return false;
        }

        final Gender that = (Gender) o;
        return this.gender.equals(that.gender);
    }

    @Override
    public int hashCode() {
        return this.gender.hashCode();
    }

    @Override
    public String toString() {
        return this.gender;
    }

    @Override
    public int compareTo(final Gender arg0) {
        return gender.compareTo(arg0.gender);
    }
}