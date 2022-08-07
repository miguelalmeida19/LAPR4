package eapli.base.productmanagement.domain.model;

public class ShoppingCartItem {
    private ProductDto productDto;
    private int quantity;

    public ShoppingCartItem(){}

    public ShoppingCartItem(ProductDto productDto, int quantity){
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
