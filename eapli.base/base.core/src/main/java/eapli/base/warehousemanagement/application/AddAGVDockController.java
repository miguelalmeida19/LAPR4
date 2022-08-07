package eapli.base.warehousemanagement.application;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddAGVDockController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVDockManagementService agvDockManagementService = AGVDockRegistry.agvDockManagementService();

    public AGVDock addagvDock(final AGVDockId agvDockId, final Squares begin, final Squares end, final Squares depth, final Accessibility accessibility) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);

        return agvDockManagementService.registerNewAGVDock(agvDockId, begin, end,depth, accessibility);
    }
}
