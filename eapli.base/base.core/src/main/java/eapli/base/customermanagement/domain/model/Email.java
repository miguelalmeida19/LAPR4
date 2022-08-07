package eapli.base.customermanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

@Embeddable
public class Email implements ValueObject, Comparable<Email> {

    private static final long serialVersionUID = 1L;

    private String email;

    public Email(final String email) {
        if (StringPredicates.isNullOrEmpty(email)) {
            throw new IllegalArgumentException(
                    "Mecanographic id should neither be null nor empty");
        }
        this.email = email;
    }

    protected Email() {
        // for ORM
    }

    public static Email valueOf(final String email) {
        return new Email(email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Email)) {
            return false;
        }

        final Email that = (Email) o;
        return this.email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public int compareTo(final Email arg0) {
        return email.compareTo(arg0.email);
    }
}
