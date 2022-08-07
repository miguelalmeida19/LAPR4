package eapli.base.app.backoffice.console.presentation.warehouses;

import eapli.base.warehousemanagement.application.UploadWarehousePlantController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UploadWarehousePlantUI extends AbstractUI {

    private final UploadWarehousePlantController theController = new UploadWarehousePlantController();

    @Override
    protected boolean doShow() {
        try {
            this.theController.uploadWarehousePlant();
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("This warehouse has already been uploaded.");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return "Upload Warehouse Plant";
    }
}
