package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.framework.validations.Invariants;

public class SurveyAnswerRegistry {
    private static SurveyAnswerManagementService surveyAnswerManagementService;

    private SurveyAnswerRegistry() {
    }

    public static void configure(final SurveyAnswerRepository surveyAnswerRepository) {
        surveyAnswerManagementService = new SurveyAnswerManagementService(surveyAnswerRepository);
    }

    public static SurveyAnswerManagementService surveyAnswerService() {
        Invariants.nonNull(surveyAnswerManagementService);
        return surveyAnswerManagementService;
    }
}
