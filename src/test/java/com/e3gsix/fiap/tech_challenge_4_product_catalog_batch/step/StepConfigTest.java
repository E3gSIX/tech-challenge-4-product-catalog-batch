package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.step;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.domain.DomainProduct;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

@ExtendWith(MockitoExtension.class)
public class StepConfigTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private ItemReader<DomainProduct> itemReader;

    @Mock
    private ItemWriter<DomainProduct> itemWriter;

    @Mock
    private ItemProcessor<DomainProduct, DomainProduct> itemProcessor;

    @InjectMocks
    private StepConfig stepConfig;

    @Test
    public void testStep() {
        Step step = stepConfig.step(jobRepository, transactionManager, itemReader, itemWriter, itemProcessor);
        assertNotNull(step);
    }
}