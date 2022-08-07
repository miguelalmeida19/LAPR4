package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WarehouseId implements ValueObject, Comparable<WarehouseId> {
    @Column(
            name = "warehouseId"
    )
    private static final long serialVersionUID = 1L;

    private String warehouseId;

    public WarehouseId(final String warehouseId){
        if(StringPredicates.isNullOrEmpty(warehouseId)){
            throw new IllegalArgumentException(
                    "Warehouse id should neither be null nor empty");
        }
        this.warehouseId = warehouseId;
    }

    protected WarehouseId(){
        //for ORM
    }

    public static WarehouseId valueOf(final String warehouseId){
        return new WarehouseId(warehouseId);
    }

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof WarehouseId)){
            return false;
        }

        final WarehouseId that= (WarehouseId) o;
        return this.warehouseId.equals(that.warehouseId);
    }

    @Override
    public int hashCode() {
        return this.warehouseId.hashCode();
    }

    @Override
    public String toString() {
        return this.warehouseId;
    }

    @Override
    public int compareTo(final WarehouseId arg0) {
        return warehouseId.compareTo(arg0.warehouseId);
    }
}
