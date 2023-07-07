package com.admin.SpringBootDepartmentalStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDepartmentalStoreApplication {

	public static void main(final String[] args) {
		SpringApplication.run(SpringBootDepartmentalStoreApplication.class, args);
	}

}
