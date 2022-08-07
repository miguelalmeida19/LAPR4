package eapli.base.infrastructure.bootstrapers;

import eapli.base.productmanagement.application.AddProductCategoryController;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.application.ListProductCategoriesController;
import eapli.base.productmanagement.application.ListProductsController;
import eapli.base.productmanagement.domain.model.Code;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.RowsId;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ProductsBootstrapperBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final AddProductCategoryController addProductCategoryController = new AddProductCategoryController();
    final ListProductCategoriesController listProductCategoriesController = new ListProductCategoriesController();
    final AddProductController addProductController = new AddProductController();
    final ListProductsController listProductsController = new ListProductsController();

    public ProductsBootstrapperBase(){
        super();
    }

    protected ProductCategory registerProductCategory(final String code, final String description){
        ProductCategory pc = null;
        try{
            pc = addProductCategoryController.addCategory(code, description);
            LOGGER.debug("»»» %s", code);
        } catch (final IntegrityViolationException | ConcurrencyException e ){
            pc = listProductCategoriesController.find(Code.valueOf(code)).orElseThrow(() -> e);
        }
        return pc;
    }

    protected Product registerProduct(final String price, final String brand, final String reference, final String shortDescription,
                                      final String longDescription, final String technicalDescription, final String barcode,
                                      final String width, final String length, final String height, final String weight,
                                      final ProductCategory productCategory, final File photo, final AisleId aisleId, final RowsId rowsId, final Long shelf ){

        Product p = null;
        try{
            p = addProductController.addProduct(price, brand, reference, shortDescription, longDescription, technicalDescription, barcode, width, length, height, weight, productCategory, photo, aisleId, rowsId, shelf);
            LOGGER.debug("»»» %s", shortDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
