package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RowsId implements ValueObject, Comparable<RowsId> {

    private static final long serialVersionUID = 1L;

    @Column(name = "rowsId")
    private String rowsId;

    public RowsId(final String rowsId){
        this.rowsId = rowsId;
    }

    protected RowsId(){
        // for ORM
    }

    public static RowsId valueOf(final String rowsId){
        return new RowsId(rowsId);
    }

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof RowsId)){
            return false;
        }

        final RowsId that = (RowsId) o;
        return this.rowsId.equals(that.rowsId);
    }

    @Override
    public int hashCode(){
        return this.rowsId.hashCode();
    }

    @Override
    public String toString(){
        return rowsId;
    }

    @Override
    public int compareTo(final RowsId agr0){
        return rowsId.compareTo(agr0.rowsId);
    }

}
