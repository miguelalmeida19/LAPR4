//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.customermanagement.application;

import eapli.base.clientusermanagement.utils.SignupUtils;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.CustomerId;
import eapli.base.customermanagement.domain.model.CustomerBuilder;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerManagementService {
    private final CustomerRepository customerRepository;

    private final SignupUtils signupUtils = new SignupUtils();

    @Autowired
    public CustomerManagementService(final CustomerRepository customerRepo) {
        this.customerRepository = customerRepo;
    }

    @Transactional
    public Customer registerNewCustomer(final String firstName, final String lastName, final String vat, final String email, final String phoneNumber, final String addresses, final String gender, final String birthdate) {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        String username = firstName.toLowerCase() + birthdate.split("-")[0];
        signupUtils.signupAndApprove(username, "Password1", firstName, lastName,
                email, "120" + birthdate.split("-")[0] + birthdate.split("-")[1]);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        customerBuilder.with(firstName, lastName, vat, email, phoneNumber, addresses, gender, birthdate, username);
        Customer newCustomer = customerBuilder.build();

        return this.customerRepository.save(newCustomer);
    }

    @Transactional
    public Customer associateSurvey(final Survey survey, final Customer customer){
        customer.surveys().add(survey);
        return this.customerRepository.save(customer);
    }

    public Iterable<Customer> activeCustomers() {
        return this.customerRepository.findByActive(true);
    }

    public Iterable<Customer> deactivatedCustomers() {
        return this.customerRepository.findByActive(false);
    }

    public Iterable<Customer> allCustomers() {
        return this.customerRepository.findAll();
    }

    public Optional<Customer> customerOfIdentity(final String id) {
        return this.customerRepository.ofIdentity(id);
    }

    public Customer customerUser(final SystemUser systemUser){
        List<Customer> list = (List<Customer>) this.customerRepository.findByUser(systemUser);
        return list.get(list.size()-1);
    }

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
