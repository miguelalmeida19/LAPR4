package eapli.base.productmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProductCategoryTest {
    @Test
    void testConstructor() {
        assertFalse((new ProductCategory()).isActive());
        assertThrows(IllegalArgumentException.class, () -> new ProductCategory(null, new Description()));
        assertThrows(IllegalArgumentException.class, () -> new ProductCategory(new Code(), null));
    }

    @Test
    void testConstructor2() {
        Code code = new Code();
        ProductCategory actualProductCategory = new ProductCategory(code, new Description());

        assertSame(code, actualProductCategory.identity());
        assertNull(actualProductCategory.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new ProductCategory()).equals(null));
        assertFalse((new ProductCategory()).equals("Different type to ProductCategory"));
    }

    @Test
    void testEquals2() {
        ProductCategory productCategory = new ProductCategory();
        assertTrue(productCategory.equals(productCategory));
        int expectedHashCodeResult = productCategory.hashCode();
        assertEquals(expectedHashCodeResult, productCategory.hashCode());
    }

    @Test
    void testEquals3() {
        ProductCategory productCategory = new ProductCategory();
        ProductCategory productCategory1 = new ProductCategory();
        assertTrue(productCategory.equals(productCategory1));
        int expectedHashCodeResult = productCategory.hashCode();
        assertEquals(expectedHashCodeResult, productCategory1.hashCode());
    }

    @Test
    void testEquals4() {
        Code code = Code.valueOf("Code");
        ProductCategory productCategory = new ProductCategory(code,
                Description.valueOf("The characteristics of someone or something"));
        assertFalse(productCategory.equals(new ProductCategory()));
    }

    @Test
    void testSameAs() {
        assertFalse((new ProductCategory()).sameAs("Other"));
    }

    @Test
    void testIdentity() {
        assertNull((new ProductCategory()).identity());
    }

    @Test
    void testDescription() {
        assertNull((new ProductCategory()).description());
    }
}

