package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Width implements ValueObject, Comparable<Width> {

    private static final long serialVersionUID = 1L;

    private Long width;

    public Width(final Long width){
        this.width = width;
    }

    protected Width(){
        //for ORM
    }

    public static Width valueOf(final Long width){
        return new Width(width);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Width)){
            return false;
        }
        final Width that = (Width) o;
        return this.width.equals(that.width);
    }

    @Override
    public int hashCode() {
        return this.width.hashCode();
    }

    @Override
    public String toString(){
        return "Width: " + width;
    }

    public int value(){
        return Integer.parseInt(String.valueOf(width));
    }

    @Override
    public int compareTo(final Width arg0) {
        return width.compareTo(arg0.width);
    }

}
