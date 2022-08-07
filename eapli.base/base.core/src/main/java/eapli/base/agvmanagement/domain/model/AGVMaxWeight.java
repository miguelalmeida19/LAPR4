package eapli.base.agvmanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AGVMaxWeight implements ValueObject, Comparable<AGVMaxWeight> {

    private static final long serialVersionUID = 1L;

    private String maxWeight;

    public AGVMaxWeight(final String maxWeight) {
        if (StringPredicates.isNullOrEmpty(maxWeight)) {
            throw new IllegalArgumentException(
                    "Mecanographic id should neither be null nor empty");
        }
        this.maxWeight = maxWeight;
    }

    protected AGVMaxWeight() {
        // for ORM
    }

    public static AGVMaxWeight valueOf(final String maxWeight) {
        return new AGVMaxWeight(maxWeight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVMaxWeight)) {
            return false;
        }

        final AGVMaxWeight that = (AGVMaxWeight) o;
        return this.maxWeight.equals(that.maxWeight);
    }

    @Override
    public int hashCode() {
        return this.maxWeight.hashCode();
    }

    @Override
    public String toString() {
        return this.maxWeight;
    }

    @Override
    public int compareTo(final AGVMaxWeight arg0) {
        return maxWeight.compareTo(arg0.maxWeight);
    }
}
