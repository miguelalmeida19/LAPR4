package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySurveyRepository extends InMemoryDomainRepository<Survey, String> implements SurveyRepository {
    public InMemorySurveyRepository(){
    }

    public Iterable<Survey> findByActive(final boolean active){
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<Survey> findByQuestionary(String questionary) {
        return this.match((e) -> {
            return e.questionary() == questionary;
        });
    }
}
