package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AisleId implements ValueObject, Comparable<AisleId> {

    private static final long serialVersionUID = 1L;

    @Column(name = "aisleId")
    private Long aisleId;

    public AisleId(final Long aisleId){
        this.aisleId = aisleId;
    }

    protected AisleId(){
        //for ORM
    }

    public static AisleId valueOf(final Long aisleId){
        return new AisleId(aisleId);
    }

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof AisleId)){
            return false;
        }

        final AisleId that = (AisleId) o;
        return this.aisleId.equals(that.aisleId);
    }

    @Override
    public int hashCode(){
        return this.aisleId.hashCode();
    }

    @Override
    public String toString(){
        return aisleId.toString();
    }

    @Override
    public int compareTo(final AisleId agr0){
        return aisleId.compareTo(agr0.aisleId);
    }
}
