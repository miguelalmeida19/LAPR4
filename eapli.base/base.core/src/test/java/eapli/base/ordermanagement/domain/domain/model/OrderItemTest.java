package eapli.base.ordermanagement.domain.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eapli.base.productmanagement.domain.model.Product;
import org.junit.jupiter.api.Test;

class OrderItemTest {


    @Test
    void testEquals() {
        assertFalse((new OrderItem()).equals(null));
        assertFalse((new OrderItem()).equals("Different type to OrderItem"));
        assertFalse((new OrderItem()).equals(0));
    }

    @Test
    void testEquals2() {
        OrderItem orderItem = new OrderItem();
        assertTrue(orderItem.equals(orderItem));
        int expectedHashCodeResult = orderItem.hashCode();
        assertEquals(expectedHashCodeResult, orderItem.hashCode());
    }

    @Test
    void testEquals3() {
        OrderItem orderItem = new OrderItem();
        OrderItem orderItem1 = new OrderItem();
        assertTrue(orderItem.equals(orderItem1));
        int expectedHashCodeResult = orderItem.hashCode();
        assertEquals(expectedHashCodeResult, orderItem1.hashCode());
    }

    @Test
    void testSameAs() {
        assertFalse((new OrderItem()).sameAs("Other"));
    }

    @Test
    void testIdentity() {
        assertNull((new OrderItem()).identity());
    }

    @Test
    void testQuantity() {
        assertNull((new OrderItem()).quantity());
    }

    @Test
    void testTotalPrice() {
        assertNull((new OrderItem()).totalPrice());
    }

    @Test
    void testProduct() {
        assertNull((new OrderItem()).product());
    }
}

