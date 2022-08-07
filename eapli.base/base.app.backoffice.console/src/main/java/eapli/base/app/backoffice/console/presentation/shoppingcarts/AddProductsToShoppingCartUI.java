package eapli.base.app.backoffice.console.presentation.shoppingcarts;

import eapli.base.app.backoffice.console.presentation.products.ListProductsFiltersUI;
import eapli.base.app.backoffice.console.presentation.products.ProductCatalogUI;
import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.productmanagement.domain.model.Product;
import eapli.base.productmanagement.domain.model.ProductDto;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.productmanagement.domain.model.ShoppingCartItem;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddProductsToShoppingCartUI extends AbstractUI {

    private boolean selectMore = true;

    private final ShoppingCart shoppingCart = new ShoppingCart();

    @Override
    protected boolean doShow() {
        try {
            while (selectMore){
                ProductCatalogUI productCatalogUI = new ProductCatalogUI();
                ListProductsFiltersUI listProductsFiltersUI = productCatalogUI.showCatalog();
                listProductsFiltersUI.show();
                final Iterable<Product> products = listProductsFiltersUI.elementsList();
                final SelectWidget<Product> selector = new SelectWidget<>("Select a Product", products, new ProductPrinter());
                selector.show();
                final Product product = selector.selectedElement();
                ProductDto productDto = new ProductDto(product.identity().toString(), product.shortDescription().toString(), Double.parseDouble(product.price().toString()));

                if (product!=null){
                    final int quantity = Console.readInteger("Quantity");
                    shoppingCart.addProduct(new ShoppingCartItem(productDto, quantity));
                }
                selectMore = false;
            }

        }catch (Exception ignored){

        }

        return false;
    }

    @Override
    public String headline() {
        return "Add Product to Shopping Cart";
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
