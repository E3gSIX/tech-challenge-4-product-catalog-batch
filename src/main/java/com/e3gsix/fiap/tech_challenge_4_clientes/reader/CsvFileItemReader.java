package com.e3gsix.fiap.tech_challenge_4_clientes.reader;

import java.io.File;
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
import com.e3gsix.fiap.tech_challenge_4_clientes.domain.DomainProduct;
import com.e3gsix.fiap.tech_challenge_4_clientes.mapper.FieldSetMapperConverter;
import com.e3gsix.fiap.tech_challenge_4_clientes.mapper.impl.FieldSetMapperConverterImpl;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CsvFileItemReader {

    @Value("${csv.file.filePath}")
    private String csvFileName;

    @Bean
    public ItemReader<DomainProduct> itemReader() {
        FieldSetMapperConverter<DomainProduct> converter = new FieldSetMapperConverterImpl<>();
        String userHome = System.getProperty("user.home");
        Path desktopDir = Paths.get(userHome, "Área de Trabalho", "arquivos");
        File file = new File(csvFileName);

        // Verificar se o diretório existe, se não, criar o diretório
        if (!Files.exists(desktopDir)) {
            log.warn("Directory does not exist: " + desktopDir + ". Creating directory.");
            try {
                Files.createDirectories(desktopDir);
                log.info("Directory created: " + desktopDir);
            } catch (IOException e) {
                log.error("Error creating directory: " + desktopDir, e);
                return new ItemReader<DomainProduct>() {
                    @Override
                    public DomainProduct read() {
                        return null;
                    }
                };
            }
        }

        // Verificar se o arquivo existe, se não, criar um arquivo vazio
        if (!file.exists()) {
            log.warn("Input resource does not exist: " + csvFileName + ". Creating an empty file.");
            try {
                if (file.createNewFile()) {
                    log.info("Empty file created: " + csvFileName);
                } else {
                    log.error("Failed to create empty file: " + csvFileName);
                }
            } catch (IOException e) {
                log.error("Error creating empty file: " + csvFileName, e);
                return new ItemReader<DomainProduct>() {
                    @Override
                    public DomainProduct read() {
                        return null;
                    }
                };
            }
        }

        log.info("CSV File Path: " + csvFileName);
        return new FlatFileItemReaderBuilder<DomainProduct>()
                .name("reader")
                .resource(new FileSystemResource(csvFileName))
                .delimited()
                .names(new String[]{"code", "description", "price", "quantity"})
                .fieldSetMapper(converter.convertFieldMapper(DomainProduct.class))
                .linesToSkip(1)
                .build();
    }
}