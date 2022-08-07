package eapli.base.app.backoffice.console.presentation.surveys;

import eapli.base.surveymanagement.application.ShowSurveyController;
import eapli.base.surveymanagement.domain.model.SaveSurveyAnswer;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

public class ShowSurveysToAnswerUI extends AbstractUI {

    private SurveysToAnswerController theController = new SurveysToAnswerController();
    private ShowSurveyController surveyController = new ShowSurveyController();

    SurveyDto survey = null;

    @Override
    protected boolean doShow() {
        List<SurveyDto> surveys = (List<SurveyDto>) theController.getSurveysToAnswer();

        final SelectWidget<SurveyDto> selector = new SelectWidget<>("Select one survey to answer", surveys, new SurveyPrinter());
        selector.show();
        SurveyDto surveyDto = selector.selectedElement();
        while (surveyDto==null){
            selector.show();
            surveyDto = selector.selectedElement();
        }
        survey = surveyDto;
        String str = new String(surveyDto.getQuestionary());
        String questionary = new String(Base64.getDecoder().decode(str));

        theController.updateQuestionnary(questionary);
        openSurvey();

        return false;
    }

    @Override
    public String headline() {
        return "Surveys that need to be answered";
    }

    private void openSurvey(){
        (new ShowSurveyController()).start();
        (new SaveSurveyAnswer(survey)).start();
        URI uri;
        try {
            uri = new URI("http://localhost:5000/survey.html");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
