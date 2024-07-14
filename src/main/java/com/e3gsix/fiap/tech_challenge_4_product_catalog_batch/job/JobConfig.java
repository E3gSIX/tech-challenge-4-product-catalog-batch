package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.listener.JobCompletionNotificationListener;

@Configuration
public class JobConfig {

	@Bean
	public Job job(Step step, JobRepository jobRepository, JobCompletionNotificationListener listener) {
		return new JobBuilder("job", jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(step)
				.build();
	}
}
