package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;


import javax.persistence.Embeddable;

@Embeddable
public class Length implements ValueObject, Comparable<Length> {

    private static final long serialVersionUID = 1L;

    private Long length;

    public Length(final Long length){
        this.length = length;
    }

    protected Length(){
        //for ORM
    }

    public static Length valueOf(final Long length){
        return new Length(length);
    }

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Length)){
            return false;
        }
        final Length that = (Length) o;
        return this.length.equals(that.length);
    }

    @Override
    public int hashCode() {
        return this.length.hashCode();
    }

    @Override
    public String toString(){
        return "Length: " + length;
    }

    public int value(){
        return Integer.parseInt(String.valueOf(length));
    }

    @Override
    public int compareTo(final Length arg0) {
        return length.compareTo(arg0.length);
    }

}
