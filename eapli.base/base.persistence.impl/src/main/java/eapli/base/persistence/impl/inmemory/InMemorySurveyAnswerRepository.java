package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySurveyAnswerRepository extends InMemoryDomainRepository<SurveyAnswer, Long> implements SurveyAnswerRepository {
    public InMemorySurveyAnswerRepository(){
    }

    public Iterable<SurveyAnswer> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }
}
