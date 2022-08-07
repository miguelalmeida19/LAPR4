package eapli.base.surveymanagement.application;

import eapli.base.agvmanagement.application.AgvManagementService;
import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.framework.validations.Invariants;

public class SurveyRegistry {
    private static SurveyManagementService surveyManagementService;

    private SurveyRegistry() {
    }

    public static void configure(final SurveyRepository surveyRepository) {
        surveyManagementService = new SurveyManagementService(surveyRepository);
    }

    public static SurveyManagementService surveyService() {
        Invariants.nonNull(surveyManagementService);
        return surveyManagementService;
    }
}
