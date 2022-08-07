package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddAgvController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AgvManagementService agvSvc = AgvRegistry.agvService();

    public AGV addAGV(String identifier, String shortDesc, String model, String maxWeight, String maxVolume, AGVDock agvDock) throws Exception {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);
        return agvSvc.addNewAgv(identifier, shortDesc, model, maxWeight, maxVolume, agvDock);
    }
}
