/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.ordermanagement.domain.domain.model;

import javax.persistence.*;
import javax.persistence.Entity;

import eapli.base.agvmanagement.application.AgvRegistry;
import eapli.base.agvmanagement.domain.model.AGV;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.domain.application.OrderItemRegistry;
import eapli.base.ordermanagement.domain.utils.PaymentConfirmation;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
public class ProductOrder implements AggregateRoot<String> {

    @Version
    private Long version;

    @GenericGenerator(name = "seq_id", strategy = "eapli.base.productmanagement.domain.model.ProductIdGenerator")

    @Id @GeneratedValue(generator = "seq_id")
    @Column(unique = true, nullable = false)
    private String orderId;
    private String shippingMethod;
    private OrderStatus orderStatus;
    private String createdOn;
    private Double finalPrice;
    private PaymentMethod paymentMethod;
    private String systemUser;
    private String createdBy;
    private AGVStatus agvStatus;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "agvIdentifier")
    private AGV agv;

    @Lob
    private byte[] receipt;

    @OneToMany(fetch = FetchType.EAGER)
    @IndexColumn(name="orderItemId")
    private List<OrderItem> orderItems = new ArrayList<>();

    private boolean active;

    public Customer getCustomer() {
        return customer;
    }

    public ProductOrder(final String shippingMethod, final OrderStatus orderStatus, final String createdOn, final Double finalPrice, final PaymentMethod paymentMethod, final List<OrderItem> orderItems, final Customer customer, final String systemUser, final String createdBy) {
        if (shippingMethod == null || orderStatus == null || createdOn == null || finalPrice == null || paymentMethod == null || orderItems == null) {
            throw new IllegalArgumentException();
        }
        this.shippingMethod = shippingMethod;
        this.orderStatus = orderStatus;
        this.createdOn = createdOn;
        this.finalPrice = finalPrice;
        this.paymentMethod = paymentMethod;
        this.orderItems = orderItems;
        this.customer = customer;
        this.systemUser = systemUser;
        this.createdBy = createdBy;
        this.agv = null;
        this.receipt = buildReceipt();
        this.agvStatus = AGVStatus.WAITING;
        this.orderStatus = OrderStatus.PROCESSING;
    }

    protected ProductOrder() {
        // for ORM only
    }

    private byte[] buildReceipt(){
        new PaymentConfirmation(orderItems, finalPrice, customer, paymentMethod.toString(), shippingMethod);
        File generator = new File("receipt.png");
        byte[] receiptInBytes = new byte[(int) generator.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(generator);
            fileInputStream.read(receiptInBytes);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiptInBytes;
    }

    @Transactional
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.orderId;
    }

    public String shippingMethod() {
        return orderShippingMethod();
    }

    private String orderShippingMethod() {
        return this.shippingMethod;
    }

    public OrderStatus orderStatus() {
        return orderState();
    }

    private OrderStatus orderState() {
        return this.orderStatus;
    }

    public String creationDate() {
        return orderCreationDate();
    }

    private String orderCreationDate() {
        return this.createdOn;
    }

    public Double finalPrice() {
        return orderFinalPrice();
    }

    private Double orderFinalPrice() {
        return this.finalPrice;
    }

    public PaymentMethod paymentMethod() {
        return orderPaymentMethod();
    }

    private PaymentMethod orderPaymentMethod() {
        return this.paymentMethod;
    }

    @Transactional
    public List<OrderItem> items() {
        return orderItems();
    }

    private List<OrderItem> orderItems() {
        return this.orderItems;
    }

    public String createdBy() {
        return orderCreatedBy();
    }

    private String orderCreatedBy() {
        return this.createdBy;
    }

    public String systemUser() {
        return orderSystemUser();
    }

    private String orderSystemUser() {
        return this.systemUser;
    }

    public AGVStatus agvStatus() {
        return orderAgvStatus();
    }

    private AGVStatus orderAgvStatus() {
        return this.agvStatus;
    }

    public AGV agv() {
        return orderAgv();
    }

    private AGV orderAgv() {
        return this.agv;
    }

    public void setAgv(AGV AGV) {
        this.agv = AGV;
    }

    public void setAgvStatus(AGVStatus agvStatus) {
        this.agvStatus = agvStatus;
    }

    @Transactional
    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }


    @Transactional
    public double getTotalWeight()
    {
        //System.out.println("Fiz o configure");
        double total=0;
        //System.out.println("Siga pro for");
        for (OrderItem order:orderItems) {
            total+=order.product().productWeight();
        }
        return total;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
