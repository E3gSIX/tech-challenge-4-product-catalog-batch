package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.service;

import java.nio.file.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;

@Service
@Slf4j
public class FileWatcherService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Value("${csv.file.filePath}")
    private String csvFilePath;

    @PostConstruct
    public void watchDirectory() {
        try {
            Path path = Paths.get(csvFilePath).getParent();
            if (path == null) {
                log.error("Invalid file path: " + csvFilePath);
                return;
            }
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            log.info("Monitoring directory for changes: " + path);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        Path createdFilePath = path.resolve((Path) event.context());
                        log.info("File created: " + createdFilePath);
                        handleFileCreation(createdFilePath);
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            log.error("Error watching directory: " + e.getMessage());
        }
    }

    public void handleFileCreation(Path createdFilePath) {
        try {
            if (createdFilePath.toAbsolutePath().toString().equals(Paths.get(csvFilePath).toAbsolutePath().toString())) {
                log.info("Detected the target file: " + createdFilePath);
                startJob();
            } else {
                log.info("Created file is not the target file: " + createdFilePath);
            }
        } catch (Exception e) {
            log.error("Error handling file creation: " + e.getMessage());
        }
    }

    private void startJob() {
        try {
            jobLauncher.run(job, new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters());
            log.info("Job started successfully.");
        } catch (Exception e) {
            log.error("Error starting job: " + e.getMessage());
        }
    }
}