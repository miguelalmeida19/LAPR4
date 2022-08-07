package eapli.base.agvmanagement.domain.model;

import eapli.base.agvdigitaltwin.SharedMemory;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.base.warehousemanagement.domain.model.Squares;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class AGV implements AggregateRoot<String> {

    @Version
    private int version;

    @Id
    private String agvIdentifier;
    @Embedded
    private AGVShortDescription shortDescription;
    @OneToOne
    private AGVModel agvModel;
    @Embedded
    private AGVMaxVolume maxVolume;
    private AGVWorkState agvWorkState;
    private boolean active;
    @OneToOne
    private AGVDock agvDockId;

    @OneToOne(fetch = FetchType.EAGER)
    @IndexColumn(name="orderId")
    private ProductOrder order;

    @OneToOne
    private Squares position;

    public AGV(final String agvIdentifier, final AGVShortDescription shortDescription, final AGVModel agvModel, final AGVMaxVolume maxVolume, final AGVDock agvDockId, final Squares position) {
        if (agvIdentifier == null || shortDescription == null || agvModel == null || maxVolume == null) {
            throw new IllegalArgumentException();
        }
        this.agvIdentifier = agvIdentifier;
        this.shortDescription = shortDescription;
        this.agvModel = agvModel;
        this.maxVolume = maxVolume;
        this.agvDockId = agvDockId;
        this.agvWorkState=AGVWorkState.AVAILABLE;
        this.order = null;
        this.position = position;
    }

    protected AGV() {
        // for ORM only
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.agvIdentifier;
    }

    public AGVShortDescription shortDescription() {
        return this.shortDescription;
    }

    public AGVModel model() {
        return this.agvModel;
    }

    public AGVMaxWeight maxWeight() {
        return this.agvModel.maxWeight();
    }

    public AGVMaxVolume maxVolume() {
        return this.maxVolume;
    }

    public AGVDockId agvDockId()
    {
        return this.agvDockId.identity();
    }

    @Transactional
    public ProductOrder order() {
        return order;
    }

    @Transactional
    public void setOrder(ProductOrder order) {
        this.order = order;
    }

    @Transactional
    public void setAgvWorkState(AGVWorkState agvWorkState) {
        this.agvWorkState = agvWorkState;
    }

    public AGVWorkState agvWorkState() {
        return workState();
    }

    private AGVWorkState workState() {
        return this.agvWorkState;
    }

    @Transactional
    public Squares agvPosition() {
        return this.position;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public boolean isActive() {
        return this.active;
    }

    @Transactional
    public void setPosition(Squares position) {
        this.position = position;

    }
}
