package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAutoTxSurveyAnswerRepository extends JpaAutoTxRepository<SurveyAnswer, Long, Long> implements SurveyAnswerRepository {

    public JpaAutoTxSurveyAnswerRepository(final TransactionalContext autoTx){
        super(autoTx, "surveyAnswerId");
    }

    public JpaAutoTxSurveyAnswerRepository(final String puname, final Map properties){
        super(puname, properties, "surveyAnswerId");
    }

    public Iterable<SurveyAnswer> findByActive(final boolean active){
        return this.match("e.active=:active", new Object[]{"active", active});
    }
}
