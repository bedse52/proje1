package com.batu.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = {"com.batu"})
@ComponentScan(basePackages = {"com.batu"})
@EnableJpaRepositories(basePackages = {"com.batu"})
public class Proje1ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(Proje1ApplicationStarter.class, args);
	}

}
