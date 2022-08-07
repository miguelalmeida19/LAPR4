package eapli.base.surveymanagement.domain.repositories;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.framework.domain.repositories.DomainRepository;

public interface SurveyAnswerRepository extends DomainRepository<Long, SurveyAnswer> {
    Iterable<SurveyAnswer> findByActive(boolean active);
}
