package eapli.base.productmanagement.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;


@Embeddable
public class ProductionCode implements ValueObject, Comparable<ProductionCode> {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    private String productionCode;

    public ProductionCode(final String productionCode) {
        if (StringPredicates.isNullOrEmpty(productionCode)) {
            throw new IllegalArgumentException(
                    "Production Code should neither be null nor empty");
        }
        this.productionCode = productionCode;
    }

    protected ProductionCode() {
        // for ORM
    }

    public static ProductionCode valueOf(final String productionCode) {
        return new ProductionCode(productionCode);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductionCode)) {
            return false;
        }

        final ProductionCode that = (ProductionCode) o;
        return this.productionCode.equals(that.productionCode);
    }

    @Override
    public int hashCode() {
        return this.productionCode.hashCode();
    }

    @Override
    public String toString() {
        return this.productionCode;
    }

    @Override
    public int compareTo(final ProductionCode arg0) {
        return productionCode.compareTo(arg0.productionCode);
    }
}