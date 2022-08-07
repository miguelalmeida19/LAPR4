package eapli.base.surveymanagement.domain.repositories;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.framework.domain.repositories.DomainRepository;

public interface SurveyRepository extends DomainRepository<String, Survey> {
    Iterable<Survey> findByActive(boolean active);
    Iterable<Survey> findByQuestionary(String questionary);
}
