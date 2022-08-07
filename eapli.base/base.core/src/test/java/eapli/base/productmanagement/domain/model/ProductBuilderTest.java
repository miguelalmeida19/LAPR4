package eapli.base.productmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProductBuilderTest {
    @Test
    void testWithPrice() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withPrice("Price"));
    }

    @Test
    void testWithPrice2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withPrice("42"));
    }

    @Test
    void testWithBrand() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withBrand("Brand"));
    }

    @Test
    void testWithBrand2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withBrand("42"));
    }

    @Test
    void testWithReference() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withReference("Reference"));
    }

    @Test
    void testWithReference2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withReference("42"));
    }

    @Test
    void testWithShortDescription() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withShortDescription("Short Description"));
    }

    @Test
    void testWithShortDescription2() {
        assertThrows(IllegalArgumentException.class,
                () -> (new ProductBuilder()).withShortDescription("eapli.base.productmanagement.domain.model.ProductBuilder"));
    }

    @Test
    void testWithShortDescription3() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withShortDescription("42"));
    }

    @Test
    void testWithLongDescription() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withLongDescription("Long Description"));
    }

    @Test
    void testWithLongDescription2() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withLongDescription("42"));
    }

    @Test
    void testWithLongDescription3() {
        assertThrows(IllegalArgumentException.class,
                () -> (new ProductBuilder()).withLongDescription(
                        "eapli.base.productmanagement.domain.model.ProductBuildereapli.base.productmanagement.domain.model"
                                + ".ProductBuilder"));
    }

    @Test
    void testWithTechnicalDescription() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withTechnicalDescription("Technical Description"));
    }

    @Test
    void testWithTechnicalDescription2() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withTechnicalDescription("42"));
    }

    @Test
    void testWithTechnicalDescription3() {
        assertThrows(IllegalArgumentException.class,
                () -> (new ProductBuilder()).withTechnicalDescription(
                        "eapli.base.productmanagement.domain.model.ProductBuildereapli.base.productmanagement.domain.model"
                                + ".ProductBuilder"));
    }

    @Test
    void testWithWidth() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withWidth("Width"));
    }

    @Test
    void testWithWidth2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withWidth("42"));
    }

    @Test
    void testWithLength() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withLength("Length"));
    }

    @Test
    void testWithLength2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withLength("42"));
    }

    @Test
    void testWithHeight() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withHeight("Height"));
    }

    @Test
    void testWithHeight2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withHeight("42"));
    }

    @Test
    void testWithWeight() {
        assertThrows(IllegalArgumentException.class, () -> (new ProductBuilder()).withWeight("Weight"));
    }

    @Test
    void testWithWeight2() {
        ProductBuilder productBuilder = new ProductBuilder();
        assertSame(productBuilder, productBuilder.withWeight("42"));
    }
}

