package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class DomainProductTest {

    @Test
    public void testDomainProduct() {
        LocalDateTime now = LocalDateTime.now();
        DomainProduct product = new DomainProduct("SmartPhone", "ASUS Zenfone 8", 2500, "Plus", 5, now);

        assertEquals("SmartPhone", product.getName());
        assertEquals("ASUS Zenfone 8", product.getDescription());
        assertEquals(2500, product.getPrice());
        assertEquals("Plus", product.getType());
        assertEquals(5, product.getQuantity());
        assertEquals(now, product.getDateInclusion());
    }
}