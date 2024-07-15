package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.job;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.listener.JobCompletionNotificationListener;

@ExtendWith(MockitoExtension.class)
public class JobConfigTest {

    @Mock
    private Step step;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobCompletionNotificationListener listener;

    @InjectMocks
    private JobConfig jobConfig;

    @Test
    public void testJob() {
        Job job = jobConfig.job(step, jobRepository, listener);
        assertNotNull(job);
    }
}