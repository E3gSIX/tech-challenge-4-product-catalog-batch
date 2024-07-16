package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;
import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.mapper.FieldSetMapperConverter;
import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.mapper.impl.FieldSetMapperConverterImpl;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CsvFileItemReader {

    @Value("${csv.file.filePath}")
    private String csvFileName;

    @Bean
    public ItemReader<DomainProduct> itemReader() {
        FieldSetMapperConverter<DomainProduct> converter = new FieldSetMapperConverterImpl<>();
        Path desktopDir = getDesktopDirectory();

        if (!verifyAndCreateDirectory(desktopDir)) {
            return emptyItemReader();
        }

        File file = new File(csvFileName);
        if (!verifyAndCreateFile(file)) {
            // If the file does not exist, create a new file named "exemplo.csv"
            file = new File(desktopDir.toFile(), "exemplo.csv");
            if (!verifyAndCreateFile(file)) {
                return emptyItemReader();
            }
        }

        log.info("CSV File Path: " + file.getAbsolutePath());
        return new FlatFileItemReaderBuilder<DomainProduct>()
                .name("reader")
                .resource(new FileSystemResource(file))
                .delimited()
                .names(new String[]{"name", "description", "price", "type", "quantity"})
                .fieldSetMapper(converter.convertFieldMapper(DomainProduct.class))
                .linesToSkip(1)
                .build();
    }

    private Path getDesktopDirectory() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, "Área de Trabalho", "arquivos");
    }

    private boolean verifyAndCreateDirectory(Path directory) {
        if (!Files.exists(directory)) {
            log.warn("Directory does not exist: " + directory + ". Creating directory.");
            try {
                Files.createDirectories(directory);
                log.info("Directory created: " + directory);
                return true;
            } catch (IOException e) {
                log.error("Error creating directory: " + directory, e);
                return false;
            }
        }
        return true;
    }

    private boolean verifyAndCreateFile(File file) {
        if (!file.exists()) {
            log.warn("Input resource does not exist: " + file.getAbsolutePath() + ". Creating an empty file.");
            try {
                if (file.createNewFile()) {
                    log.info("Empty file created: " + file.getAbsolutePath());
                    // Write the header to the new file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("name,description,price,type,quantity");
                        writer.newLine();
                    } catch (IOException e) {
                        log.error("Error writing header to file: " + file.getAbsolutePath(), e);
                        return false;
                    }
                    return true;
                } else {
                    log.error("Failed to create empty file: " + file.getAbsolutePath());
                    return false;
                }
            } catch (IOException e) {
                log.error("Error creating empty file: " + file.getAbsolutePath(), e);
                return false;
            }
        }
        return true;
    }

    private ItemReader<DomainProduct> emptyItemReader() {
        return new ItemReader<DomainProduct>() {
            @Override
            public DomainProduct read() {
                return null;
            }
        };
    }
}