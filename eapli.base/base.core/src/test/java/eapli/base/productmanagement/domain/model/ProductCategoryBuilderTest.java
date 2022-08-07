package eapli.base.productmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ProductCategoryBuilderTest {


    @Test
    void testWith() {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        assertSame(productCategoryBuilder,
                productCategoryBuilder.with("Code", "The characteristics of someone or something"));
    }

    @Test
    void testWith2() {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        assertSame(productCategoryBuilder, productCategoryBuilder.with("Category code should neither be null nor empty",
                "Category code should neither be null nor empty"));
    }

    @Test
    void testWithCode() {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        assertSame(productCategoryBuilder, productCategoryBuilder.withCode("Code"));
    }

    @Test
    void testWithDescription() {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        assertSame(productCategoryBuilder,
                productCategoryBuilder.withDescription("The characteristics of someone or something"));
    }

    @Test
    void testBuild() {
        ProductCategoryBuilder productCategoryBuilder = new ProductCategoryBuilder();
        productCategoryBuilder.withCode("Code");
        productCategoryBuilder.withDescription("Description");
        assertEquals("Description", productCategoryBuilder.build().toString());
    }
}

