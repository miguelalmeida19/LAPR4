package eapli.base.app.backoffice.console.presentation.shoppingcarts;

import com.fasterxml.jackson.databind.ObjectMapper;
import eapli.base.productmanagement.domain.model.ShoppingCart;
import eapli.base.productmanagement.domain.model.ShoppingCartItem;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.OrdersService;

import java.util.List;

public class AddProductsShoppingCartController {

    private ShoppingCart shoppingCart;

    private final OrdersService ordersService = new OrdersService();

    public Object createShoppingCartAndRequestCatalog() {
        try {
            shoppingCart = (ShoppingCart) this.ordersService.sendMessageToServer(SPOMSP.Code.SHOPPINGCART.code, null);
            return shoppingCart;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Object addProductToShoppingCart() {
        try {
            ObjectMapper Obj = new ObjectMapper();
            // Converting the Java object into a JSON string
            String jsonStr;
            // Displaying Java object into a JSON string
            AddProductsToShoppingCartUI addProductsToShoppingCartUI = new AddProductsToShoppingCartUI();
            addProductsToShoppingCartUI.show();
            List<ShoppingCartItem> map = addProductsToShoppingCartUI.getShoppingCart().getShoppingCart();
            ShoppingCartItem mapEntry = map.get(0);
            // Converting the Java object into a JSON string
            jsonStr = Obj.writeValueAsString(mapEntry);
            // Displaying Java object into a JSON string
            shoppingCart = (ShoppingCart) this.ordersService.sendMessageToServer(SPOMSP.Code.ADD_TO_SHOPPINGCART.code, jsonStr);
            return shoppingCart;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<ShoppingCartItem> getShoppingCartContent() {
        return shoppingCart.getShoppingCart();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (ShoppingCartItem e : shoppingCart.getShoppingCart()){
            totalPrice+=e.getProductDto().getPrice()*e.getQuantity();
        }
        return totalPrice;
    }
}
