package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.productmanagement.domain.model.ProductDto;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.app.backoffice.console.presentation.shoppingcarts.AddProductsShoppingCartController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.*;

public class ShoppingCartUI extends AbstractUI {

    private final AddProductsShoppingCartController theController = new AddProductsShoppingCartController();

    private ShoppingCart shoppingCart = null;

    @Override
    protected boolean doShow() {

        shoppingCart = (ShoppingCart) this.theController.createShoppingCartAndRequestCatalog();
        int option = 1;

        shoppingCart = (ShoppingCart) theController.addProductToShoppingCart();

        do {
            final Iterable<String> options = Arrays.asList(new String[]{"Add Product to Shopping Cart", "See shopping Cart"});
            final SelectWidget<String> selector = new SelectWidget<>("Select what you want to do.", options);
            selector.show();
            String selected = selector.selectedElement();
            try {
                if (selected.equals("Add Product to Shopping Cart")){
                    option = 1;
                    shoppingCart = (ShoppingCart) theController.addProductToShoppingCart();
                }
                if (selected.equals("See shopping Cart")) {
                    option = 1;
                    System.out.printf("   %30s%30s%30s\n", "PRODUCT ID", "QUANTITY","UNIT PRICE");
                    for (Object entry:shoppingCart.getShoppingCart()){
                        LinkedHashMap shoppingCartItem = (LinkedHashMap) entry;
                        LinkedHashMap productDto = (LinkedHashMap) shoppingCartItem.get("productDto");
                        ProductDto productDto1 = new ProductDto((String) productDto.get("productId"), (String) productDto.get("shortDescription"), (Double) productDto.get("price"));
                        Integer quantity = (Integer) shoppingCartItem.get("quantity");
                        System.out.printf("   %30s%30s%30s\n", productDto1.getProductId(), quantity, productDto1.getPrice());
                    }
                    System.out.println("\nTotal Price: " + theController.getTotalPrice() + "€\n\n");
                }
            }catch (Exception e){
                option = 0;
            }

        }while (option!=0);

        return false;
    }

    @Override
    public String headline() {
        return "Shopping Cart";
    }
}
