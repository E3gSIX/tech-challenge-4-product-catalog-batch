package com.e3gsix.fiap.tech_challenge_4_clientes.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

import com.e3gsix.fiap.tech_challenge_4_clientes.domain.DomainProduct;

public class EnjetorProces {

	@Bean
	public ItemProcessor<DomainProduct, DomainProduct> itemProcessor() {
		return new Processar();
	}

}
