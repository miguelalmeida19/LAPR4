package eapli.base.app.backoffice.console.presentation.warehouses;

import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.visitor.Visitor;

public class AGVDockPrinter implements Visitor<AGVDock> {

    @Override
    public void visit(final AGVDock visitee) {
        System.out.printf("%30s", visitee.identity());
    }
}