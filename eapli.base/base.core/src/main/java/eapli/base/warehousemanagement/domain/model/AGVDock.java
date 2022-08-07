package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.dom4j.dom.DOMAttributeNodeMap;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class AGVDock implements AggregateRoot<AGVDockId> {

    @Version
    private Long version;
    @EmbeddedId
    private AGVDockId agvDockId;
    @OneToMany
    private List<Squares> squares;
    @Embedded
    private Accessibility accessibility;
    private boolean active;

    public AGVDock(final AGVDockId agvDockId, final Squares begin, final Squares end, final Squares depth, final Accessibility accessibility) {
        if(agvDockId == null || begin == null || end == null || depth == null || accessibility == null){
            throw new IllegalArgumentException();
        }
        this.agvDockId = agvDockId;
        this.squares = Arrays.asList(new Squares[]{begin, end, depth});
        this.accessibility = accessibility;
    }

    protected AGVDock(){
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
    public AGVDockId identity(){
        return this.agvDockId;
    }

    public Squares begin(){
        return agvDockBegin();
    }
    private Squares agvDockBegin(){
        return this.squares.get(0);
    }

    public Squares end(){
        return agvDockEnd();
    }
    private Squares agvDockEnd(){
        return this.squares.get(1);
    }

    public Squares depth(){
        return agvDockDepth();
    }
    private Squares agvDockDepth(){
        return this.squares.get(2);
    }

    public Accessibility accessibility(){
        return agvDockAccessibility();
    }
    private Accessibility agvDockAccessibility(){
        return this.accessibility;
    }

    public boolean isActive() {
        return this.active;
    }
}
