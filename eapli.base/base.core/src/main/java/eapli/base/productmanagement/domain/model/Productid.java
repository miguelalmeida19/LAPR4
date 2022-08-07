package eapli.base.productmanagement.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class Productid implements ValueObject, Comparable<Productid> {

    private static final long serialVersionUID = 1L;

    @GeneratedValue @Column(name = "productId")
    private String productId;

    public Productid(final String Productid) {
        if (StringPredicates.isNullOrEmpty(Productid)) {
            throw new IllegalArgumentException(
                    "Product id should neither be null nor empty");
        }
        this.productId = Productid;
    }

    protected Productid() {
        // for ORM
    }

    public static Productid valueOf(final String Productid) {
        return new Productid(Productid);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Productid)) {
            return false;
        }

        final Productid that = (Productid) o;
        return this.productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return this.productId.hashCode();
    }

    @Override
    public String toString() {
        return this.productId;
    }

    @Override
    public int compareTo(final Productid arg0) {
        return productId.compareTo(arg0.productId);
    }
}