package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

@Embeddable
public class Accessibility implements ValueObject, Comparable<Accessibility> {

    private static final long serialVersionUID = 1L;

    private String accessibility;

    public Accessibility(final String accessibility){
        if(StringPredicates.isNullOrEmpty(accessibility)){
            throw new IllegalArgumentException(
                    "Aisles accessibility should not be null nor empty");
        }
        this.accessibility = accessibility;
    }

    protected Accessibility(){
        // for ORM
    }

    public static Accessibility valueOf(final String accessibility){
        return new Accessibility(accessibility);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accessibility)) {
            return false;
        }

        final Accessibility that = (Accessibility) o;
        return this.accessibility.equals(that.accessibility);
    }

    @Override
    public int hashCode() {
        return this.accessibility.hashCode();
    }

    @Override
    public String toString() {
        return this.accessibility;
    }

    @Override
    public int compareTo(final Accessibility arg0) {
        return accessibility.compareTo(arg0.accessibility);
    }

}
