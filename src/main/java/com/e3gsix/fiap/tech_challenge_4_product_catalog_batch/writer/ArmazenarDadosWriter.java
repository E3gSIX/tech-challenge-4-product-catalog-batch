package com.e3gsix.fiap.tech_challenge_4_clientes.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.e3gsix.fiap.tech_challenge_4_clientes.domain.DomainProduct;

@Configuration
@EnableTransactionManagement
public class ArmazenarDadosWriter {

    @Bean
    public ItemWriter<DomainProduct> itemWriter(DataSource dataSource) {
        
    	System.out.println(dataSource);
    	return new JdbcBatchItemWriterBuilder<DomainProduct>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .dataSource(dataSource)
                .sql("INSERT INTO domainproduct "
                		+ "(code, description, price, quantity) "
                		+ " VALUES (:code, :description, :price, :quantity)")
                .build();
    }
}