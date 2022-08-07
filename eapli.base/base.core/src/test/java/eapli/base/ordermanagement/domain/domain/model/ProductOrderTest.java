package eapli.base.ordermanagement.domain.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eapli.base.customermanagement.domain.model.Customer;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ProductOrderTest {
    @Test
    void testConstructor() {
        ProductOrder actualProductOrder = new ProductOrder();
        assertNull(actualProductOrder.getCustomer());
        assertFalse(actualProductOrder.isActive());
    }




    @Test
    void testEquals() {
        assertFalse((new ProductOrder()).equals(null));
        assertFalse((new ProductOrder()).equals("Different type to ProductOrder"));
        assertFalse((new ProductOrder()).equals(0));
    }

    @Test
    void testEquals2() {
        ProductOrder productOrder = new ProductOrder();
        assertTrue(productOrder.equals(productOrder));
        int expectedHashCodeResult = productOrder.hashCode();
        assertEquals(expectedHashCodeResult, productOrder.hashCode());
    }

    @Test
    void testEquals3() {
        ProductOrder productOrder = new ProductOrder();
        ProductOrder productOrder1 = new ProductOrder();
        assertTrue(productOrder.equals(productOrder1));
        int expectedHashCodeResult = productOrder.hashCode();
        assertEquals(expectedHashCodeResult, productOrder1.hashCode());
    }

    @Test
    void testSameAs() {
        assertFalse((new ProductOrder()).sameAs("Other"));
    }

    @Test
    void testIdentity() {
        assertNull((new ProductOrder()).identity());
    }

    @Test
    void testShippingMethod() {
        assertNull((new ProductOrder()).shippingMethod());
    }

    @Test
    void testOrderStatus() {
        assertNull((new ProductOrder()).orderStatus());
    }

    @Test
    void testCreationDate() {
        assertNull((new ProductOrder()).creationDate());
    }

    @Test
    void testFinalPrice() {
        assertNull((new ProductOrder()).finalPrice());
    }

    @Test
    void testPaymentMethod() {
        assertNull((new ProductOrder()).paymentMethod());
    }

    @Test
    void testItems() {
        assertTrue((new ProductOrder()).items().isEmpty());
    }

    @Test
    void testCreatedBy() {
        assertNull((new ProductOrder()).createdBy());
    }

    @Test
    void testSystemUser() {
        assertNull((new ProductOrder()).systemUser());
    }
}

