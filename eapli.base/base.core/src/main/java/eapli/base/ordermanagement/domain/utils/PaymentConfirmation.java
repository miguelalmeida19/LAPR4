package eapli.base.ordermanagement.domain.utils;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.*;
import java.io.File;
import java.util.List;

public class PaymentConfirmation {
    private List<OrderItem> orderItems;
    private Double finalPrice;
    private Customer customer;
    private File receipt;
    private String html;

    private String header = "<center> <div class=\"div\">\n" +
            "<p><br /></p>\n" +
            "  <div class=\"div-2\"><h1>&nbsp;&nbsp;&nbsp;&nbsp;Payment Confirmation&nbsp;&nbsp;&nbsp;&nbsp;</h1></div>\n" +
            "  <div class=\"div-3\"><h2>Customer: name</h2></div>\n";

    private String footer = "<div class=\"div-16\"><h2>Total: &lt;valor&gt;</h2></div>" +
            "</div></center>";

    private String product = "<div class=\"div-12\">\n" +
            "    <div class=\"builder-columns div-13\">\n" +
            "      <div class=\"builder-column column\">\n" +
            "        <div class=\"div-14\">\n" +
            "          <p>&lt;Product's name&gt; → &lt;Total Price&gt;</p>" +
            "          <p>&lt;Quantity&gt; x &lt;Unit Price&gt;</p>" +
            "          <p>------------------------------------</p>\n" +
            "        </div>\n" +
            "  </div>\n";

    public PaymentConfirmation(List<OrderItem> orderItemList, Double finalPrice, Customer customer, String paymentMethod, String shippingMethod) {
        this.orderItems = orderItemList;
        this.finalPrice = finalPrice;

        String head = header.replace("name", customer.name().toString());
        String foot = footer.replace("&lt;valor&gt;", String.valueOf(finalPrice) + "€");
        String payment = footer.replace("&lt;valor&gt;", paymentMethod)
                .replace("Total", "Payment Method");
        String shipping = footer.replace("&lt;valor&gt;", shippingMethod)
                .replace("Total", "ShippingMethod Method");
        StringBuilder htmlFinal = new StringBuilder();
        htmlFinal.append(head);
        for (OrderItem orderItem : orderItemList) {
            String item = product.replace("&lt;Product's name&gt;", orderItem.product().shortDescription().toString())
                    .replace("&lt;Total Price&gt;", String.valueOf(orderItem.totalPrice()) + "€")
                    .replace("&lt;Quantity&gt;", String.valueOf(orderItem.quantity()))
                    .replace("&lt;Unit Price&gt;", String.valueOf(orderItem.product().price().toString()) + "€");
            htmlFinal.append(item);
        }
        htmlFinal.append(payment);
        htmlFinal.append(shipping);
        htmlFinal.append(foot);

        HtmlImageGenerator hig = new HtmlImageGenerator();
        hig.setSize(new Dimension(10, 1000));
        hig.loadHtml(htmlFinal.toString());

        hig.saveAsImage(new File("receipt.png"));
    }
}
