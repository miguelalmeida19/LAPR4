package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AGVDockId implements ValueObject, Comparable<AGVDockId> {

    private static final long serialVersionUID = 1L;

    @Column(name = "agvDockId")

    private String agvDockId;

    public AGVDockId(final String agvDockId){
        if(StringPredicates.isNullOrEmpty(agvDockId)){
            throw new IllegalArgumentException(
                    "AGV dock ID should neither be null nor empty");
        }
        this.agvDockId = agvDockId;
    }

    protected AGVDockId(){
        // for ORM
    }

    public static AGVDockId valueOf(final String agvDockId){
        return new AGVDockId(agvDockId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AGVDockId)) {
            return false;
        }

        final AGVDockId that = (AGVDockId) o;
        return this.agvDockId.equals(that.agvDockId);
    }

    @Override
    public int hashCode() {
        return this.agvDockId.hashCode();
    }

    @Override
    public String toString() {
        return this.agvDockId;
    }

    @Override
    public int compareTo(final AGVDockId arg0) {
        return agvDockId.compareTo(arg0.agvDockId);
    }

}
