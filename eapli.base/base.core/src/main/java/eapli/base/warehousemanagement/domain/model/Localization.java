package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@Embeddable
public class Localization implements ValueObject, Comparable<Square> {

    private static final long serialVersionUID = 1L;

    @OneToOne
    private Squares begin;

    @OneToOne
    private Squares end;


    public Localization(final Squares begin, final Squares end){
        this.begin = begin;
        this.end = end;
    }

    protected Localization(){
        //for ORM
    }

    public static Localization valueOf(final Squares begin, final Squares end){
        return new Localization(begin, end);
    }

    public boolean equals(final Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Localization)){
            return false;
        }
        final Localization that = (Localization) o;
        return this.begin.equals(that.begin) && this.end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString(){
        return begin + " " + end;
    }


    @Override
    public int compareTo(Square o) {
        return 0;
    }

    public Squares begin() {
        return begin;
    }

    public Squares end() {
        return end;
    }
}
