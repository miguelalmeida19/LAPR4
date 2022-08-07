package eapli.base.warehousemanagement.domain.model;

import eapli.base.customermanagement.domain.model.Name;
import eapli.framework.domain.model.AggregateRoot;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Squares implements AggregateRoot<Long> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long squaresId;

    private String lsquare;

    private String wsquare;

    private boolean active;

    public Squares(final Long lsquare, final Long wsquare) {
        if (lsquare==null || wsquare==null ) {
            throw new IllegalArgumentException(
                    "Square should neither be null nor empty");
        }
        this.lsquare = String.valueOf(lsquare);
        this.wsquare = String.valueOf(wsquare);
    }

    protected Squares() {
        // for ORM
    }

    public static Squares valueOf(final Long lsquare, final Long wsquare) {
        return new Squares(lsquare, wsquare);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final Squares that = (Squares) o;
        return (this.wsquare.equals(that.wsquare) && this.lsquare.equals(that.lsquare));
    }

    @Override
    public int hashCode() {
        return Objects.hash(lsquare, wsquare);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String toString() {
        return this.lsquare + " " + this.wsquare;
    }

    @Override
    public int compareTo(Long other) {
        return AggregateRoot.super.compareTo(other);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public Long identity() {
        return squaresId;
    }

    public String getLsquare() {
        return lsquare;
    }

    public String getWsquare() {
        return wsquare;
    }
}
