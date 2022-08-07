package eapli.base.customermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Birthdate implements ValueObject, Comparable<Birthdate> {

    private static final long serialVersionUID = 1L;

    private String birth;

    public Birthdate(final String birth) {
        this.birth = birth;
    }

    protected Birthdate() {
        // for ORM
    }

    public static Birthdate valueOf(final String birth) {
        return new Birthdate(birth);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Birthdate)) {
            return false;
        }

        final Birthdate that = (Birthdate) o;
        return this.birth.equals(that.birth);
    }

    @Override
    public int hashCode() {
        return this.birth.hashCode();
    }

    @Override
    public String toString() {
        return this.birth;
    }

    @Override
    public int compareTo(final Birthdate arg0) {
        return birth.compareTo(arg0.birth);
    }
}