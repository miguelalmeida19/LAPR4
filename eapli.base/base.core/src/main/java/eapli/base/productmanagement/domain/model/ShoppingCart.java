package eapli.base.productmanagement.domain.model;

import java.util.*;

public class ShoppingCart {
    private List<ShoppingCartItem> shoppingCart = new ArrayList<>();

    public ShoppingCart(List<ShoppingCartItem> shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart(){
    }

    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingCartItem> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addProduct(ShoppingCartItem shoppingCartItem){
        shoppingCart.add(shoppingCartItem);
    }

    public void removeProduct(ProductDto product){
        shoppingCart.remove(product);
    }

    private double getFinalPrice(){
        double totalPrice = 0;
        for (ShoppingCartItem entry : shoppingCart){
            totalPrice += entry.getProductDto().getPrice()*entry.getQuantity();
        }
        return totalPrice;
    }
}