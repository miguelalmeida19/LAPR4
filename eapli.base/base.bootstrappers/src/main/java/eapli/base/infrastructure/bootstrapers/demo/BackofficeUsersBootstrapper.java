package eapli.base.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;

import eapli.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class BackofficeUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerSalesClerk("tozean", PASSWORD1, "Tó", "Zé", "toze@ipp.pt");
        registerWarehouseEmployee("zezoca", PASSWORD1, "Ze", "Zoca", "zezoca@ipp.pt");
        registerSalesManager("fabio", PASSWORD1, "Fábio", "Costa", "fabio@ipp.pt");
        return true;
    }

    private void registerSalesClerk(final String username, final String password,
                                    final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.SALES_CLERK);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerWarehouseEmployee(final String username, final String password,
                                    final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.WAREHOUSE_EMPLOYEE);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerSalesManager(final String username, final String password,
                                           final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.SALES_MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

}
