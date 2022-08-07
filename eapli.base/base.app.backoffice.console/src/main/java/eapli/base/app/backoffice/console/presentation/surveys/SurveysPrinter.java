package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.visitor.Visitor;

public class SurveysPrinter implements Visitor<Survey> {

    @Override
    public void visit(Survey visitee) {
            System.out.printf("%30s", visitee.identity());
    }
}
