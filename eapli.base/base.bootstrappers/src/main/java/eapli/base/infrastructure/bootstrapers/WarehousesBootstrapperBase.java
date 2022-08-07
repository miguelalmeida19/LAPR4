package eapli.base.infrastructure.bootstrapers;

import eapli.base.warehousemanagement.application.UploadWarehousePlantController;
import eapli.base.warehousemanagement.domain.model.Warehouse;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WarehousesBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final UploadWarehousePlantController uploadWarehousePlantController = new UploadWarehousePlantController();

    public WarehousesBootstrapperBase(){
        super();
    }

    protected void uploadWarehouse(){
        try{
            uploadWarehousePlantController.uploadWarehousePlant();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
