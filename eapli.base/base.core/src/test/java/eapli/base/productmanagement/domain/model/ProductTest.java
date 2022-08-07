package eapli.base.productmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import eapli.base.warehousemanagement.domain.model.AisleId;
import eapli.base.warehousemanagement.domain.model.RowsId;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        assertNull(actualProduct.getAisles());
        assertNull(actualProduct.getRow());
        assertFalse(actualProduct.isActive());
    }



    @Test
    void testEquals() {
        assertFalse((new Product()).equals(null));
        assertFalse((new Product()).equals("Different type to Product"));
        assertFalse((new Product()).equals(0));
    }

    @Test
    void testEquals2() {
        Product product = new Product();
        assertTrue(product.equals(product));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product.hashCode());
    }

    @Test
    void testEquals3() {
        Product product = new Product();
        Product product1 = new Product();
        assertTrue(product.equals(product1));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product1.hashCode());
    }

    @Test
    void testSameAs() {
        assertFalse((new Product()).sameAs("Other"));
    }

    @Test
    void testIdentity() {
        assertNull((new Product()).identity());
    }

    @Test
    void testPrice() {
        assertNull((new Product()).price());
    }

    @Test
    void testBrand() {
        assertNull((new Product()).brand());
    }

    @Test
    void testReference() {
        assertNull((new Product()).reference());
    }

    @Test
    void testShortDescription() {
        assertNull((new Product()).shortDescription());
    }

    @Test
    void testLongDescription() {
        assertNull((new Product()).longDescription());
    }

    @Test
    void testTechnicalDescription() {
        assertNull((new Product()).technicalDescription());
    }

    @Test
    void testProductionCode() {
        assertNull((new Product()).productionCode());
    }

    @Test
    void testBarcode() {
        assertNull((new Product()).barcode());
    }

    @Test
    void testWidth() {
        assertNull((new Product()).width());
    }

    @Test
    void testLength() {
        assertNull((new Product()).length());
    }

    @Test
    void testHeight() {
        assertNull((new Product()).height());
    }

    @Test
    void testWeight() {
        assertNull((new Product()).productWeight());
    }

    @Test
    void testCategory() {
        assertNull((new Product()).category());
    }

    @Test
    void testProdCat() throws UnsupportedEncodingException {
        Price price = Price.valueOf("Price");
        Brand brand = Brand.valueOf("Brand");
        Reference reference = Reference.valueOf("Reference");
        Shortdescription shortDescription = Shortdescription.valueOf("Shortdescription");
        Longdescription longDescription = Longdescription.valueOf("Longdescription");
        Technicaldescription technicalDescription = Technicaldescription.valueOf("Technicaldescription");
        Barcode barcode = Barcode.valueOf("Barcode");
        ProductionCode productionCode = ProductionCode.valueOf("Production Code");
        Code code = Code.valueOf("Code");
        ProductCategory productCategory = new ProductCategory(code,
                Description.valueOf("The characteristics of someone or something"));

        byte[] photo = "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8");
        AisleId aisleId = new AisleId(123L);
        assertEquals("The characteristics of someone or something",
                (new Product(price, brand, reference, shortDescription, longDescription, technicalDescription, barcode,
                        productionCode, 10.0, 10.0, 10.0, 10.0, productCategory, photo, aisleId, new RowsId("42"), 1L)).prodCat());
    }

    @Test
    void testPhoto() {
        assertNull((new Product()).photo());
    }

    @Test
    void testAisles() {
        assertNull((new Product()).aisles());
    }

    @Test
    void testRowsId() {
        assertNull((new Product()).rowsId());
    }

    @Test
    void testShelfId() {
        assertNull((new Product()).shelfId());
    }
}

