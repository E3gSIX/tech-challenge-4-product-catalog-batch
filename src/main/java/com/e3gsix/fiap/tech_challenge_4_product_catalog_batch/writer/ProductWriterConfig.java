package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;

@Configuration
@EnableTransactionManagement
public class ProductWriterConfig {

    @Bean
    public ItemWriter<DomainProduct> itemWriter(MongoTemplate mongoTemplate) {
        MongoItemWriter<DomainProduct> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("domainproduct");
        return writer;
    }
}