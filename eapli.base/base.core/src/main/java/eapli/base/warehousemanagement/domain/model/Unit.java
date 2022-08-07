package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Unit implements ValueObject, Comparable<Unit> {

    private static final long serialVersionUID = 1L;

    private String unit;

    public Unit(final String unit){
        if(StringPredicates.isNullOrEmpty(unit)){
            throw new IllegalArgumentException(
                    "Unit description should neither be null or empty");
        }
        this.unit=unit;
    }

    protected Unit(){
        //for ORM
    }

    public static Unit valueOf(final String unit){
        return new Unit(unit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unit)) {
            return false;
        }

        final Unit that = (Unit) o;
        return this.unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        return this.unit.hashCode();
    }

    @Override
    public String toString() {
        return this.unit;
    }


    @Override
    public int compareTo(final Unit arg0) {
        return unit.compareTo(arg0.unit);
    }
}
