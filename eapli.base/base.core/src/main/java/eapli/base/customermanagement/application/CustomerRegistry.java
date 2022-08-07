//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.framework.validations.Invariants;

public final class CustomerRegistry {

    private static CustomerManagementService customerService;

    private CustomerRegistry() {
    }

    public static void configure(final CustomerRepository customerRepo) {
        customerService = new CustomerManagementService(customerRepo);
    }

    public static CustomerManagementService customerService() {
        Invariants.nonNull(customerService);
        return customerService;
    }
}
