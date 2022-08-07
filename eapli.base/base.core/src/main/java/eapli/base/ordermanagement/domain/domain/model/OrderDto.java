package eapli.base.ordermanagement.domain.domain.model;

public class OrderDto {
    private String orderId;
    private String finalPrice;
    private String orderStatus;

    public OrderDto(String orderId, String finalPrice, String orderStatus){
        this.orderId = orderId;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId='" + orderId + '\'' +
                ", finalPrice='" + finalPrice + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}