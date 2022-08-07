package eapli.base.productmanagement.domain.model;

public class ProductDto {
    private String productId;
    private String shortDescription;
    private double price;

    public ProductDto(){}

    public ProductDto(String productId, String shortDescription, double price){
        this.productId = productId;
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId='" + productId + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
