package eapli.base.agvmanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class AGVMaxVolume implements ValueObject, Comparable<AGVMaxVolume> {

    private static final long serialVersionUID = 1L;

    private String maxVolume;

    public AGVMaxVolume(final String maxVolume) {
        if (StringPredicates.isNullOrEmpty(maxVolume)) {
            throw new IllegalArgumentException(
                    "Mecanographic id should neither be null nor empty");
        }
        this.maxVolume = maxVolume;
    }

    protected AGVMaxVolume() {
        // for ORM
    }

    public static AGVMaxVolume valueOf(final String maxVolume) {
        return new AGVMaxVolume(maxVolume);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVMaxVolume)) {
            return false;
        }

        final AGVMaxVolume that = (AGVMaxVolume) o;
        return this.maxVolume.equals(that.maxVolume);
    }

    @Override
    public int hashCode() {
        return this.maxVolume.hashCode();
    }

    @Override
    public String toString() {
        return this.maxVolume;
    }

    @Override
    public int compareTo(final AGVMaxVolume arg0) {
        return maxVolume.compareTo(arg0.maxVolume);
    }
}
