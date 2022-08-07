//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.customermanagement.domain.repositories;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public interface CustomerRepository extends DomainRepository<String, Customer> {
    Iterable<Customer> findByActive(boolean active);

    Iterable<Customer> findByUser(SystemUser systemUser);

    List<Customer> findByAgeLower(int age);
    List<Customer> findByAgeHigher(int age);
    List<Customer> findByGender(String gender);
}
