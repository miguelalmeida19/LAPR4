package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse implements AggregateRoot<String> {

    @Version
    private Long version;

    @GenericGenerator(name= "w_id", strategy = "eapli.base.warehousemanagement.domain.model.WarehouseIdGenerator")

    @Id @GeneratedValue(generator = "w_id")
    private String warehouseId;
    @Embedded
    private Description description;
    @Embedded
    private Length length;
    @Embedded
    private Width width;
    @Embedded
    private Square square;
    @Embedded
    private Unit unit;

    @OneToMany
    private List<Aisles> aislesList;
    @OneToMany
    private List<AGVDock> agvDocksList;

    private boolean active;

    public Warehouse(final Description description, final Length length, final Width width, final Square square, final Unit unit, final List<Aisles> aislesList, final List<AGVDock> agvDocksList){
        if(description == null || length == null || width == null || square == null || unit == null || aislesList == null || agvDocksList == null){
            throw new IllegalArgumentException();
        }
        this.description = description;
        this.length = length;
        this.width = width;
        this.unit = unit;
        this.square = square;
        this.aislesList = aislesList;
        this.agvDocksList = agvDocksList;
    }

    protected Warehouse(){
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
    public String identity(){
        return this.warehouseId;
    }

    public Description description(){
        return warehouseDescription();
    }

    private Description warehouseDescription(){
        return this.description;
    }

    public Length length(){
        return warehouseLength();
    }

    private Length warehouseLength(){
        return this.length;
    }

    public Width width(){
        return warehouseWidth();
    }

    private Width warehouseWidth(){
        return this.width;
    }

    public Square square(){
        return warehouseSquare();
    }

    private Square warehouseSquare(){
        return this.square;
    }

    public Unit unit(){
        return warehouseUnit();
    }

    private Unit warehouseUnit(){
        return this.unit;
    }

    public List<Aisles> aislesList(){
        return warehouseAislesList();
    }

    private List<Aisles> warehouseAislesList(){
        return this.aislesList;
    }

    public List<AGVDock> agvDockList(){
        return warehouseAgvDockList();
    }

    private List<AGVDock> warehouseAgvDockList(){
        return this.agvDocksList;
    }

    public boolean isActive(){
        return this.active;
    }
}
