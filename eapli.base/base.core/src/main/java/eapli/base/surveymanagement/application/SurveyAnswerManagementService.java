package eapli.base.surveymanagement.application;

import eapli.base.customermanagement.application.CustomerRegistry;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyAnswer;
import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SurveyAnswerManagementService {
    private final SurveyAnswerRepository surveyAnswerRepository;

    @Autowired
    public SurveyAnswerManagementService(final SurveyAnswerRepository surveyAnswerRepository) {
        this.surveyAnswerRepository = surveyAnswerRepository;
    }

    @Transactional
    public SurveyAnswer addNewSurveyAnswer(final String answer, final Survey survey, final Customer customer) throws Exception {
        SurveyAnswer surveyAnswer = new SurveyAnswer(answer);
        SurveyAnswer newSurveyAnswer = surveyAnswerRepository.save(surveyAnswer);
        survey.surveyAnswers().add(newSurveyAnswer);
        SurveyRegistry.surveyService().getSurveyRepository().save(survey);
        List<Survey> surveyList = customer.surveys();
        for (int i=0; i<surveyList.size(); i++){
            if (surveyList.get(i).sameAs(survey)){
                customer.surveys().remove(i);
            }
        }
        saveCustomerUpdated(customer);
        return newSurveyAnswer;
    }

    @Transactional
    public void saveCustomerUpdated(Customer customer){
        CustomerRegistry.customerService().getCustomerRepository().save(customer);
    }

    public Iterable<SurveyAnswer> activeSurveyAnswers() {
        return this.surveyAnswerRepository.findByActive(true);
    }

    public Iterable<SurveyAnswer> deactivatedSurveyAnswers() {
        return this.surveyAnswerRepository.findByActive(false);
    }

    public Iterable<SurveyAnswer> allSurveyAnswers() {
        return this.surveyAnswerRepository.findAll();
    }
}
