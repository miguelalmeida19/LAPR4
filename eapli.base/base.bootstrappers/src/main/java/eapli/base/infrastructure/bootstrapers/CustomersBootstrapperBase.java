package eapli.base.infrastructure.bootstrapers;

import eapli.base.customermanagement.application.AddCustomerController;
import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomersBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersBootstrapperBase.class);

    final AddCustomerController addCustomerController = new AddCustomerController();
    final ListCustomersController listCustomersController = new ListCustomersController();

    public CustomersBootstrapperBase(){
        super();
    }

    protected Customer registerCustomer(final String firstName, final String lastName,
                                        final String vat,
                                        final String email, final String phoneNumber, final String addresses, final String gender, final String birthdate){

        Customer c = null;
        try{
            c = addCustomerController.addCustomer(firstName, lastName, vat, email, phoneNumber, addresses, gender, birthdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
