package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.listener;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.JobExecution;

@ExtendWith(MockitoExtension.class)
public class JobCompletionNotificationListenerTest {

    @Mock
    private JobExecution jobExecution;

    @InjectMocks
    private JobCompletionNotificationListener listener;

    @BeforeEach
    public void setUp() throws Exception {
        setField(listener, "filePath", "test.csv");
        setField(listener, "processedDir", "processed");
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testAfterJob() throws Exception {
        Path sourcePath = Paths.get("test.csv");
        
        // Check if the file exists and delete it if it does
        if (Files.exists(sourcePath)) {
            Files.delete(sourcePath);
        }
        
        // Create the file
        Files.createFile(sourcePath);

        listener.afterJob(jobExecution);

        // No need to verify jobExecution.getStatus() as it's not called in afterJob
        Files.deleteIfExists(sourcePath);
    }
}