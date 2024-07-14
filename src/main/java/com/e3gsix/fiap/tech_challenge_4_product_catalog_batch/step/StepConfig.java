package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;

@Configuration
public class StepConfig {

	@Bean
	public Step step(
			JobRepository jobRepository, 
			PlatformTransactionManager transactionManager,
			ItemReader<DomainProduct> itemReader, 
			ItemWriter<DomainProduct> itemWriter,
			ItemProcessor<DomainProduct, DomainProduct> itemProcessor) {
		return new StepBuilder("step", jobRepository)
				.<DomainProduct, DomainProduct>chunk(10, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}
}
