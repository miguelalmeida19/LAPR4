package eapli.base.agvmanagement.domain.model;

import eapli.base.customermanagement.domain.model.Name;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AGVModel implements AggregateRoot<Long> {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agvModelId;

    @Embedded
    private AGVMaxWeight agvMaxWeight;

    private long autonomy;
    private Integer maxVelocity;

    private boolean active;

    public AGVModel(final AGVMaxWeight maxWeight) {
        if (maxWeight == null) {
            throw new IllegalArgumentException(
                    "AGVModel should neither be null nor empty");
        }
        this.agvMaxWeight = maxWeight;
        this.autonomy = 20000/Long.parseLong(maxWeight.toString());
        double part = (autonomy*1.0/100);
        double part1 = (1.5);
        this.maxVelocity = (int) (part + part1);
    }

    protected AGVModel() {
        // for ORM
    }

    public static AGVModel valueOf(final AGVMaxWeight maxWeight) {
        return new AGVModel(maxWeight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }

        final AGVModel that = (AGVModel) o;
        return (this.agvMaxWeight.equals(that.agvMaxWeight) && this.autonomy == (that.autonomy) && this.maxVelocity == (that.maxVelocity));
    }

    @Override
    public int hashCode() {
        return Objects.hash(agvMaxWeight, autonomy, maxVelocity);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }


    @Override
    public String toString() {
        return "AGVModel{" +
                "version=" + version +
                ", agvModelId=" + agvModelId +
                ", maxWeight=" + agvMaxWeight +
                ", autonomy=" + autonomy +
                ", max_velocity=" + maxVelocity +
                ", active=" + active +
                '}';
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
        return agvModelId;
    }

    public AGVMaxWeight maxWeight() {
        return agvMaxWeight;
    }

    public Integer maxVelocity() {
        return maxVelocity;
    }
}
