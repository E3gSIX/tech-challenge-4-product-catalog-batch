package com.e3gsix.fiap.tech_challenge_4_clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.e3gsix.fiap.tech_challenge_4_clientes.service.FileWatcherService;

@SpringBootApplication
@EnableScheduling
public class BatchApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(BatchApplication.class, args);
//	}

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BatchApplication.class, args);
        FileWatcherService fileWatcherService = context.getBean(FileWatcherService.class);
        fileWatcherService.watchDirectory();
    }
}
