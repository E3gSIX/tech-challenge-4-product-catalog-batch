package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.writer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ProductWriterConfigTest {

    @Test
    void testItemWriter() {
        // Arrange
        MongoTemplate mongoTemplate = mock(MongoTemplate.class);
        ProductWriterConfig productWriterConfig = new ProductWriterConfig();

        // Act
        var itemWriter = productWriterConfig.itemWriter(mongoTemplate);

        // Assert
        assertNotNull(itemWriter, "The itemWriter should not be null");
    }
}