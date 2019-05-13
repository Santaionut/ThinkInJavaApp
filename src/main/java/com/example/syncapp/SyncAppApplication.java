package com.example.syncapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.syncapp.repository")
@EntityScan("com.example.syncapp.model")
@SpringBootApplication
public class SyncAppApplication {

	public static void main(String[] args) {
		System.out.println("Start application");
		SpringApplication.run(SyncAppApplication.class, args);
	}

}
