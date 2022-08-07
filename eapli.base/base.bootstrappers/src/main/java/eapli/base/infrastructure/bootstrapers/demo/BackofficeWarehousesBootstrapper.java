package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.WarehousesBootstrapperBase;
import eapli.framework.actions.Action;

public class BackofficeWarehousesBootstrapper extends WarehousesBootstrapperBase implements Action {

    @Override
    public boolean execute(){
        uploadWarehouse();
        return  true;
    }
}
