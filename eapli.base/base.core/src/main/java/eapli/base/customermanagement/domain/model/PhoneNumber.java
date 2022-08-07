package eapli.base.customermanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

@Embeddable
public class PhoneNumber implements ValueObject, Comparable<PhoneNumber> {

    private static final long serialVersionUID = 1L;

    private String phoneNumber;

    public PhoneNumber(final String phoneNumber) {
        if (StringPredicates.isNullOrEmpty(phoneNumber)) {
            throw new IllegalArgumentException(
                    "Mecanographic id should neither be null nor empty");
        }
        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber() {
        // for ORM
    }

    public static PhoneNumber valueOf(final String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        final PhoneNumber that = (PhoneNumber) o;
        return this.phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return this.phoneNumber.hashCode();
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }

    @Override
    public int compareTo(final PhoneNumber arg0) {
        return phoneNumber.compareTo(arg0.phoneNumber);
    }
}