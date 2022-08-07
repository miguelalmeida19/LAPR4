package eapli.base.warehousemanagement.application;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.base.warehousemanagement.domain.model.AGVDockId;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@UseCaseController
public class ListAGVDocksController
{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVDockManagementService agvDockManagementService = AGVDockRegistry.agvDockManagementService();

    public Iterable<AGVDock> allagvDocks() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.SALES_CLERK, BaseRoles.POWER_USER);

        return agvDockManagementService.allAGVDocks();
    }

    public Optional<AGVDock> find(final AGVDockId u) {
        return agvDockManagementService.agvDockOfIdentity(u);
    }
}
