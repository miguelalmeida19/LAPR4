package eapli.base.infrastructure.bootstrapers;

import eapli.base.customermanagement.application.AddCustomerController;
import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.surveymanagement.application.CreateSurveyController;
import eapli.base.surveymanagement.domain.model.Survey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SurveyBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyBootstrapperBase.class);

    final CreateSurveyController createSurveyController = new CreateSurveyController();

    public SurveyBootstrapperBase(){
        super();
    }

    protected Survey registerSurvey(final String surveyCode, final String description, final int period, final List<String> rules, final String questionary){

        Survey c = null;
        try{
            c = createSurveyController.addSurvey(surveyCode, description, period, rules, questionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
