package eapli.base.productmanagement.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Code implements ValueObject, Comparable<Code> {

    private static final long serialVersionUID = 1L;

    @GeneratedValue @Column(name = "categoryCode")
    private String code;

    public Code(final String code) {
        if (StringPredicates.isNullOrEmpty(code)) {
            throw new IllegalArgumentException(
                    "Category code should neither be null nor empty");
        }
        this.code = code;
    }

    protected Code() {
        // for ORM
    }

    public static Code valueOf(final String code) {
        return new Code(code);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Code)) {
            return false;
        }

        final Code that = (Code) o;
        return this.code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public int compareTo(final Code arg0) {
        return code.compareTo(arg0.code);
    }
}