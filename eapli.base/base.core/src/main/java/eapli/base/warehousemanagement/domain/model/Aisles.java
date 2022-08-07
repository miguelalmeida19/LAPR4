package eapli.base.warehousemanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Entity
public class Aisles implements AggregateRoot<AisleId> {

    @Version
    private Long version;

    @EmbeddedId
    private AisleId aisleId;

    @OneToMany
    private List<Squares> squares;

    @Embedded
    private Accessibility accessibility;

    @OneToMany
    private List<AisleRow> rowsList;

    private boolean active;

    public Aisles(AisleId aisleId, List<Squares> squares, Accessibility accessibility, List<AisleRow> rowsList) {
        if (aisleId == null || squares == null || accessibility == null || rowsList == null) {
            throw new IllegalArgumentException();
        }
        this.aisleId = aisleId;
        this.squares = squares;
        this.accessibility = accessibility;
        this.rowsList = rowsList;
    }

    protected Aisles() {
        // for ORM
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
    public AisleId identity() {
        return this.aisleId;
    }

    public Squares begin() {
        return aisleBegin();
    }

    private Squares aisleBegin() {
        return this.squares.get(0);
    }

    public Squares end() {
        return aisleEnd();
    }

    private Squares aisleEnd() {
        return this.squares.get(1);
    }

    public Squares depth() {
        return aisleDepth();
    }

    private Squares aisleDepth() {
        return this.squares.get(2);
    }

    public Accessibility accessibility() {
        return aisleAccessibility();
    }

    private Accessibility aisleAccessibility() {
        return this.accessibility;
    }

    public List<AisleRow> rows() {
        return aisleRows();
    }

    private List<AisleRow> aisleRows() {
        return this.rowsList;
    }

    public boolean isActive() {
        return this.active;
    }

}
