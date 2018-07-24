package com.elife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPostgreSQLApplication {

	public static void main(String[] args) {
		System.out.println("application starts ");
		SpringApplication.run(SpringBootPostgreSQLApplication.class, args);
	}
}
