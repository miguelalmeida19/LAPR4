package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.base.warehousemanagement.domain.repositories.AGVDockRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxSurveyRepository extends JpaAutoTxRepository<Survey, String, String> implements SurveyRepository {

    public JpaAutoTxSurveyRepository(final TransactionalContext autoTx){
        super(autoTx, "surveyCode");
    }

    public JpaAutoTxSurveyRepository(final String puname, final Map properties){
        super(puname, properties, "surveyCode");
    }

    public Iterable<Survey> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<Survey> findByQuestionary(String questionary) {
        return this.match("e.questionary=:questionary", new Object[]{"questionary", questionary});
    }
}
