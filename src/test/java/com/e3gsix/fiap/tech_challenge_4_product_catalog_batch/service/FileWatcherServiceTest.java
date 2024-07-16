package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.service;

import static org.mockito.Mockito.*;

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
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;

@ExtendWith(MockitoExtension.class)
public class FileWatcherServiceTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job job;

    @InjectMocks
    private FileWatcherService fileWatcherService;

    @BeforeEach
    public void setUp() throws Exception {
        setField(fileWatcherService, "csvFilePath", "test.csv");
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testHandleFileCreation() throws Exception {
        Path path = Paths.get("test.csv");

        // Check if the file exists and delete it if it does
        if (Files.exists(path)) {
            Files.delete(path);
        }

        // Create the file
        Files.createFile(path);

        fileWatcherService.handleFileCreation(path);

        verify(jobLauncher, times(1)).run(eq(job), any());

        Files.deleteIfExists(path);
    }
}