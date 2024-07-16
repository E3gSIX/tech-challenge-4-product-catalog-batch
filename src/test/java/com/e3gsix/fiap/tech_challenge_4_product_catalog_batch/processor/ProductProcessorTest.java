package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.processor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;

@ExtendWith(MockitoExtension.class)
public class ProductProcessorTest {

    @InjectMocks
    private ProductProcessor processor;

    private DomainProduct validProduct;
    private DomainProduct invalidProduct;

    @BeforeEach
    public void setUp() {
        validProduct = new DomainProduct("SmartPhone", "ASUS Zenfone 8", 2500, "Plus", 5, LocalDateTime.now());
        invalidProduct = new DomainProduct("", "", -1, "", -1, null);
    }

    @Test
    public void testProcessValidProduct() throws Exception {
        DomainProduct processedProduct = processor.process(validProduct);
        assertNotNull(processedProduct);
    }

    @Test
    public void testProcessInvalidProduct() throws Exception {
        DomainProduct processedProduct = processor.process(invalidProduct);
        assertNull(processedProduct);
    }
}