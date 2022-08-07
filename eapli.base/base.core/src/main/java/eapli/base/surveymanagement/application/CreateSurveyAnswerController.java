package eapli.base.surveymanagement.application;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.model.SurveyDto;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CreateSurveyAnswerController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SurveyAnswerManagementService surveyAnswerManagementService = SurveyAnswerRegistry.surveyAnswerService();


    public SurveyAnswer addSurveyAnswer(final String answer, final Survey survey, final Customer customer) throws Exception {

        return surveyAnswerManagementService.addNewSurveyAnswer(answer, survey, customer);
    }
}