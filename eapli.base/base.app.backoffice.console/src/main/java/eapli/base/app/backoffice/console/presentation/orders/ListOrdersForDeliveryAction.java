package eapli.base.app.backoffice.console.presentation.orders;

import eapli.framework.actions.Action;


public class ListOrdersForDeliveryAction implements Action{

    public boolean execute(){
        return new ListOrdersForDeliveryUI().show();
    }
}
