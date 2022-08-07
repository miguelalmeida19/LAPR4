package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Shelf implements ValueObject, Comparable<Shelf> {

    private static final long serialVersionUID = 1L;

    private Long shelf;

    public Shelf(final Long shelf){
        this.shelf = shelf;
    }

    protected Shelf(){
        //for ORM
    }

    public static Shelf valueOf(final Long shelf){
        return new Shelf(shelf);
    }

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Shelf)){
            return false;
        }
        final Shelf that = (Shelf) o;
        return this.shelf.equals(that.shelf);
    }

    @Override
    public int hashCode() {
        return this.shelf.hashCode();
    }

    @Override
    public String toString(){
        return shelf.toString();
    }

    @Override
    public int compareTo(final Shelf arg0) {
        return shelf.compareTo(arg0.shelf);
    }

}
