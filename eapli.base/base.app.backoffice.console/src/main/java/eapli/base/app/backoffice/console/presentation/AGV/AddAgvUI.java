package eapli.base.app.backoffice.console.presentation.AGV;

import eapli.base.agvmanagement.application.AddAgvController;
import eapli.base.app.backoffice.console.presentation.products.ProductCategoryPrinter;
import eapli.base.app.backoffice.console.presentation.warehouses.AGVDockPrinter;
import eapli.base.productmanagement.application.ListProductCategoriesController;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.warehousemanagement.application.ListAGVDocksController;
import eapli.base.warehousemanagement.domain.model.AGVDock;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddAgvUI extends AbstractUI {

    private final AddAgvController theController = new AddAgvController();
    private final ListAGVDocksController theController1 = new ListAGVDocksController();

    @Override
    protected boolean doShow() {
        final Iterable<AGVDock> agvDocks = theController1.allagvDocks();
        final SelectWidget<AGVDock> selector = new SelectWidget<>("Select an AGV Dock", agvDocks, new AGVDockPrinter());
        selector.show();
        final AGVDock agvDock = selector.selectedElement();
        final String identifier = Console.readLine("Insert the Identifier (alphanumeric code with 8 characters): ");
        final String shortDesc = Console.readLine("Insert a short description (30 characters maximum): ");
        final String model = Console.readLine("Insert the model (50 characters maximum): ");
        final String maxWeight = Console.readLine("Insert the maximum weight it can carry (number in KG): ");
        final String maxVolume = Console.readLine("Insert the maximum volume it can carry (number in cubic meters): ");
        try {
            this.theController.addAGV(identifier, shortDesc, model, maxWeight, maxVolume, agvDock);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("AGV ID or Dock ID is already in use.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

            return false;
    }

    @Override
    public String headline() {
        return "Add AGV";
    }
}
