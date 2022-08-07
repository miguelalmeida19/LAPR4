package eapli.base.surveymanagement.application;

import eapli.base.agvmanagement.application.AgvManagementService;
import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CreateSurveyController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SurveyManagementService surveyManagementService = SurveyRegistry.surveyService();


    public Survey addSurvey(final String code, final String description, final int period, final List<String> rules, final String questionary) throws Exception {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.POWER_USER);

        return surveyManagementService.addNewSurvey(code, description, period, rules, questionary);
    }
}