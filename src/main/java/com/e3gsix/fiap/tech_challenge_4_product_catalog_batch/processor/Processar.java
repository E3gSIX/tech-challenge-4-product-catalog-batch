package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;

@Configuration
public class Processar implements ItemProcessor<DomainProduct, DomainProduct> {

	@Override
	public DomainProduct process(DomainProduct item) throws Exception {
		System.out.println("Processing: " + item.getCode() + " " + item.getDescription());
		return item;
	}
}
