package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.listener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    @Value("${csv.file.filePath}")
    private String filePath;

    @Value("${csv.file.processedDir}")
    private String processedDir;

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        try {
            Path sourcePath = Paths.get(filePath);
            if (Files.exists(sourcePath)) {
                Path targetPath = Paths.get(processedDir, sourcePath.getFileName().toString());
                if (!Files.exists(Paths.get(processedDir))) {
                    Files.createDirectories(Paths.get(processedDir));
                }
                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                log.info("File moved to: " + targetPath.toString());
            } else {
                log.warn("File moved to: " + filePath);
            }
        } catch (Exception e) {
            log.error("Error moving file: " + e.getMessage());
        }
    }
}