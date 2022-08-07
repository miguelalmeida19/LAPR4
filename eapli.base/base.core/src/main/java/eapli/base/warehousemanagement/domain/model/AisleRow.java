package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

@Entity(name = "AisleRow")
public class AisleRow implements AggregateRoot<RowsId> {

    @Version
    private Long version;

    @EmbeddedId
    private RowsId rowsId;
    @OneToMany
    private List<Squares> localization;
    @Embedded
    private Shelf shelf;

    private boolean active;

    public AisleRow(final RowsId rowsId, final Squares begin, final Squares end, final Shelf shelf){
        if(rowsId == null || begin == null || end == null || shelf == null){
            throw new IllegalArgumentException();
        }
        this.rowsId = rowsId;
        this.localization = Arrays.asList(new Squares[]{begin, end});;
        this.shelf = shelf;
    }

    protected AisleRow(){
        // for ORM
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other){
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public RowsId identity(){
        return this.rowsId;
    }

    public Squares begin(){
        return rowBegin();
    }
    private Squares rowBegin(){
        return this.localization.get(0);
    }

    public Squares end(){
        return rowEnd();
    }
    private Squares rowEnd(){
        return this.localization.get(1);
    }

    public Shelf shelf(){
        return rowShelf();
    }
    private Shelf rowShelf(){
        return this.shelf;
    }

    public boolean isActive() {
        return this.active;
    }
}
