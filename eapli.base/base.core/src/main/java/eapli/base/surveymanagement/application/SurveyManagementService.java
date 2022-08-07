package eapli.base.surveymanagement.application;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.application.CustomerManagementService;
import eapli.base.customermanagement.application.CustomerRegistry;
import eapli.base.customermanagement.domain.model.Birthdate;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.application.OrderItemManagementService;
import eapli.base.ordermanagement.domain.domain.application.OrderItemRegistry;
import eapli.base.ordermanagement.domain.domain.application.OrderManagementService;
import eapli.base.ordermanagement.domain.domain.application.OrderRegistry;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.productmanagement.application.ProductRegistry;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.Shortdescription;
import eapli.base.spomsp.SPOMSP;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.model.SurveyRule;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class SurveyManagementService {
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyManagementService(final SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Transactional
    public Survey addNewSurvey(String code, String description, int period, List<String> rules, String questionary) throws Exception {
        Survey newSurvey = new Survey(code, description, period, rules, questionary);
        int c = 0;
        List<String> opt = Arrays.stream(SurveyRule.values()).map(Enum::toString).collect(Collectors.toList());
        for (String r : rules) {
            for (String op : opt){
                if (r.contains(op)){
                    c++;
                    break;
                }
            }
        }
        if (c > 0) {
            Survey survey = this.surveyRepository.save(newSurvey);

            // find users that meet the rules of the survey
            CustomerManagementService customerManagementService = CustomerRegistry.customerService();
            OrderItemManagementService orderItemManagementService = OrderItemRegistry.orderItemService();
            OrderManagementService orderManagementService = OrderRegistry.orderService();
            List<Customer> customers = (List<Customer>) customerManagementService.allCustomers();

            for (Customer customer : customers) {
                for (String rule : rules) {
                    if (rule.toLowerCase().contains(SurveyRule.AGE_HIGHER.toString())) {
                        try {
                            int age = Integer.parseInt(rule.split(" ")[3]);
                            LocalDateTime date = LocalDateTime.now();
                            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            date = date.minusYears(age);
                            String d = date.format(formatter).split("-")[2];
                            if (Integer.parseInt(customer.birthDate().toString().split("-")[2])<=Integer.parseInt(d)){
                                if (!customer.surveys().contains(survey)){
                                    customer.surveys().add(survey);
                                    survey.customers().add(customer);
                                    customerManagementService.getCustomerRepository().save(customer);
                                    break;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (rule.toLowerCase().contains(SurveyRule.AGE_LOWER.toString())) {
                        try {
                            int age = Integer.parseInt(rule.split(" ")[3]);
                            LocalDateTime date = LocalDateTime.now();
                            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            date = date.minusYears(age);
                            String d = date.format(formatter).split("-")[2];
                            if (Integer.parseInt(customer.birthDate().toString().split("-")[2])>=Integer.parseInt(d)){
                                if (!customer.surveys().contains(survey)){
                                    customer.surveys().add(survey);
                                    survey.customers().add(customer);
                                    customerManagementService.getCustomerRepository().save(customer);
                                    break;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (rule.toLowerCase().contains(SurveyRule.GENDER.toString())) {
                        try {
                            String gender = rule.split(" ")[2].toLowerCase(Locale.ROOT);
                            if (customer.gender().toString().toLowerCase().equals(gender)){
                                if (!customer.surveys().contains(survey)){
                                    customer.surveys().add(survey);
                                    survey.customers().add(customer);
                                    customerManagementService.getCustomerRepository().save(customer);
                                    break;
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    } else if (rule.toLowerCase().contains(SurveyRule.ORDERED_PRODUCT_ID.toString())) {
                        try {
                            String productShortDescription = rule.split(":")[1];
                            Product product = ProductRegistry.productService().findByShortDescription(Shortdescription.valueOf(productShortDescription)).iterator().next();
                            List<ProductOrder> productOrders = customer.orders();
                            for (ProductOrder productOrder: productOrders){
                                List<OrderItem> orderItems = productOrder.items();
                                for (OrderItem orderItem : orderItems){
                                    if (orderItem.product().shortDescription().toString().equals(product.shortDescription().toString())){
                                        if (!customer.surveys().contains(survey)){
                                            customer.surveys().add(survey);
                                            survey.customers().add(customer);
                                            customerManagementService.getCustomerRepository().save(customer);
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
            return survey;
        } else {
            throw new IllegalArgumentException("The rules you typed are not valid");
        }

    }

    public Iterable<Survey> activeSurveys() {
        return this.surveyRepository.findByActive(true);
    }

    public Iterable<Survey> deactivatedSurveys() {
        return this.surveyRepository.findByActive(false);
    }

    public Iterable<Survey> allSurveys() {
        return this.surveyRepository.findAll();
    }

    public Iterable<Survey> findByQuestionary(String questionary) {
        return this.surveyRepository.findByQuestionary(questionary);
    }

    public SurveyRepository getSurveyRepository() {
        return surveyRepository;
    }
}
