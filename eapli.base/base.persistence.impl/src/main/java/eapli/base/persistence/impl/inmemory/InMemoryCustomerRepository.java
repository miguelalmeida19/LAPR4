//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, String> implements CustomerRepository {
    public InMemoryCustomerRepository() {
    }

    public Iterable<Customer> findByActive(final boolean active) {
        return this.match((e) -> {
            return e.isActive() == active;
        });
    }

    @Override
    public Iterable<Customer> findByUser(SystemUser systemUser) {
        return this.match((e) -> {
            return e.systemUser() == systemUser;
        });
    }

    @Override
    public List<Customer> findByAgeLower(int age) {
        return null;
    }

    @Override
    public List<Customer> findByAgeHigher(int age) {
        return null;
    }

    @Override
    public List<Customer> findByGender(String gender) {
        return null;
    }
}
