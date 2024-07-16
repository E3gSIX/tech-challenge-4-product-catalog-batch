package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;

@ExtendWith(MockitoExtension.class)
public class MongoConfigTest {

    @InjectMocks
    private MongoConfig mongoConfig;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri = "mongodb://localhost:27017";

    @Value("${spring.data.mongodb.database}")
    private String databaseName = "test_db";

    @Value("${spring.data.mongodb.username}")
    private String username = "test_user";

    @Value("${spring.data.mongodb.password}")
    private String password = "test_pass";

    @BeforeEach
    public void setUp() throws Exception {
        setField(mongoConfig, "mongoUri", mongoUri);
        setField(mongoConfig, "databaseName", databaseName);
        setField(mongoConfig, "username", username);
        setField(mongoConfig, "password", password);
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testMongoClient() {
        MongoClient client = mongoConfig.mongoClient();
        assertNotNull(client);
    }

    @Test
    public void testMongoTemplate() {
        MongoTemplate template = mongoConfig.mongoTemplate();
        assertNotNull(template);
    }
}