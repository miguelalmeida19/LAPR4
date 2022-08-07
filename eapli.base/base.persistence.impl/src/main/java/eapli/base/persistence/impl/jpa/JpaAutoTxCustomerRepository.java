//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.model.Birthdate;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.customermanagement.domain.model.Gender;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JpaAutoTxCustomerRepository extends JpaAutoTxRepository<Customer, String, String> implements CustomerRepository {
    public JpaAutoTxCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "customerId");
    }

    public JpaAutoTxCustomerRepository(final String puname, final Map properties) {
        super(puname, properties, "customerId");
    }

    public Iterable<Customer> findByActive(final boolean active) {
        return this.match("e.active=:active", new Object[]{"active", active});
    }

    @Override
    public Iterable<Customer> findByUser(SystemUser systemUser) {
        return this.match("e.systemUser=:systemUser", new Object[]{"systemUser", systemUser});
    }

    @Override
    public List<Customer> findByAgeLower(int age) {
        LocalDateTime date = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = date.minusYears(age);
        final Birthdate birth = Birthdate.valueOf(date.format(formatter));

        return this.match("e.birth>:birth", new Object[]{"birth", birth});
    }

    @Override
    public List<Customer> findByAgeHigher(int age) {
        LocalDateTime date = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = date.minusYears(age);
        final Birthdate birth = Birthdate.valueOf(date.format(formatter));

        return this.match("e.birth<:birth", new Object[]{"birth", birth});
    }

    @Override
    public List<Customer> findByGender(String gender) {
        return this.match("e.gender:=gender", new Object[]{"gender", gender});
    }
}
