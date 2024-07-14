package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;
import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.util.FileUtil;
import java.time.LocalDateTime;

@Configuration
public class ProductProcessorConfig {
    @Bean
    public ItemProcessor<DomainProduct, DomainProduct> itemProcessor() {
        return new ProductProcessor();
    }
}

class ProductProcessor implements ItemProcessor<DomainProduct, DomainProduct> {
    private static final String ERROR_FILE = "invalid_records.txt";

    @Override
    public DomainProduct process(DomainProduct item) throws Exception {
        StringBuilder errorMessages = new StringBuilder();

        if (item.getName() == null || item.getName().isEmpty()) {
            errorMessages.append("Name cannot be null or empty; ");
        }
        if (item.getDescription() == null || item.getDescription().isEmpty()) {
            errorMessages.append("Description cannot be null or empty; ");
        }
        if (item.getPrice() <= 0) {
            errorMessages.append("Price must be greater than zero; ");
        }
        if (item.getType() == null || item.getType().isEmpty()) {
            errorMessages.append("Type cannot be null or empty; ");
        }
        if (item.getQuantity() < 0) {
            errorMessages.append("Quantity cannot be negative; ");
        }
        if (item.getDateInclusion() == null) {
            item.setDateInclusion(LocalDateTime.now());
        }

        if (errorMessages.length() > 0) {
            FileUtil.writeToFile(ERROR_FILE, "Nme: " + item.getName() + ", Description: " + item.getDescription() + ", Errors: " + errorMessages.toString());
            return null;
        }

        System.out.println("Processing: " + item.getName() + " " + item.getDescription()+ " " + item.getPrice()+ " " + item.getType()+ " " + item.getQuantity()+ " " + item.getDateInclusion());
        return item;
    }
}