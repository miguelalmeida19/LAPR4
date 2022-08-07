package eapli.base.surveymanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@Entity
public class SurveyAnswer implements AggregateRoot<Long> {

    @Version
    private int version;

    private boolean active;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long surveyAnswerId;

    @Column(columnDefinition="LONGTEXT")
    private String answer;

    public SurveyAnswer(final String answer){
        this.answer = answer;
    }

    protected SurveyAnswer() {
        // for ORM only
    }

    public String answer() {
        return answer;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return surveyAnswerId;
    }

    public int getVersion() {
        return version;
    }

    public boolean isActive() {
        return this.active;
    }
}
