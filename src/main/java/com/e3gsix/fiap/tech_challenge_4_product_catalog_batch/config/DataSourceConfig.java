package com.e3gsix.fiap.tech_challenge_4_clientes.config;

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

	private void testDatabaseConnection() {
		try (Connection connection = springDataSource().getConnection()) {
			log.info("Conexão com o banco de dados estabelecida com sucesso!");
		} catch (SQLException e) {
			log.error("Falha ao estabelecer conexão com o banco de dados: " + e.getMessage());
		}
	}
}