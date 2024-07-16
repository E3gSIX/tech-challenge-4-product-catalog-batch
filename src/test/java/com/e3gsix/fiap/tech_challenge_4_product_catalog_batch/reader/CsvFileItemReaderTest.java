package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.reader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.ItemReader;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;

@ExtendWith(MockitoExtension.class)
public class CsvFileItemReaderTest {

    @InjectMocks
    private CsvFileItemReader reader;

    @BeforeEach
    public void setUp() throws Exception {
        setField(reader, "csvFileName", "test.csv");
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testItemReader() throws Exception {
        Path path = Paths.get("test.csv");
        Files.createFile(path);

        ItemReader<DomainProduct> itemReader = reader.itemReader();
        assertNotNull(itemReader);

        Files.deleteIfExists(path);
    }
}