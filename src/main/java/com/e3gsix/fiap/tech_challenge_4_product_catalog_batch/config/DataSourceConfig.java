package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDataSource() {
        return DataSourceBuilder.create().build();
    }

    @PostConstruct
    public void validateDataSource() {
        testDatabaseConnection();
    }

    void testDatabaseConnection() { // Changed visibility to package-private
        try (Connection connection = springDataSource().getConnection()) {
            log.info("Connection to the database successfully established!");
        } catch (SQLException e) {
            log.error("Failed to establish connection to the database: " + e.getMessage());
        }
    }
}