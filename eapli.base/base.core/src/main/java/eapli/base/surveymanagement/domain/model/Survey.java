package eapli.base.surveymanagement.domain.model;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.warehousemanagement.domain.model.Aisles;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey implements AggregateRoot<String> {

    @Version
    private int version;

    private boolean active;
    @Id
    private String surveyCode;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    @ElementCollection
    private List<String> rules;
    @Column(columnDefinition="LONGTEXT")
    private String questionary;

    @OneToMany
    private List<SurveyAnswer> surveyAnswers = new ArrayList<>();

    @ManyToMany(mappedBy = "surveys", fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();

    public Survey(final String surveyCode, final String description, final int period, final List<String> rules, final String questionary) {
        this.surveyCode = surveyCode;
        this.description = description;
        this.start = LocalDateTime.now();
        this.end = start.plusDays(period);
        this.rules = rules;
        this.questionary = questionary;
    }

    protected Survey() {
        // for ORM only
    }

    public String description() {
        return description;
    }

    public LocalDateTime start() {
        return start;
    }

    public LocalDateTime end() {
        return end;
    }

    public List<String> rules() {
        return rules;
    }

    public String questionary() {
        return questionary;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.surveyCode;
    }

    public int getVersion() {
        return version;
    }

    public boolean isActive() {
        return this.active;
    }

    public List<Customer> customers() {
        return customers;
    }

    public List<SurveyAnswer> surveyAnswers() {
        return surveyAnswers;
    }
}
