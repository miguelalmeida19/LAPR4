package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.visitor.Visitor;

public class SurveyPrinter implements Visitor<SurveyDto> {
    @Override
    public void visit(SurveyDto visitee) {
        System.out.printf("%30s", visitee.getDescription());
    }
}
