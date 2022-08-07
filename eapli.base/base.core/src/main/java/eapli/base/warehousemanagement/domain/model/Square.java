package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Square implements ValueObject, Comparable<Square> {

    private static final long serialVersionUID = 1L;

    private Long square;

    public Square(final Long square){
        this.square = square;
    }

    protected Square(){
        //for ORM
    }

    public static Square valueOf(final Long square){
        return new Square(square);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Square)){
            return false;
        }
        final Square that = (Square) o;
        return this.square.equals(that.square);
    }

    @Override
    public int hashCode() {
        return this.square.hashCode();
    }

    @Override
    public String toString(){
        return "Square: " + square;
    }

    public int value(){
        return Integer.parseInt(String.valueOf(square));
    }

    @Override
    public int compareTo(final Square arg0) {
        return square.compareTo(arg0.square);
    }

}
