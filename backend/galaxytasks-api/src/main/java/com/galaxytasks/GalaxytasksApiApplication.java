package com.galaxytasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.galaxytasks.administration.AdminProperties;

@SpringBootApplication(scanBasePackages = "com.galaxytasks")
@EnableJpaRepositories(basePackages = "com.galaxytasks.repository")
@EnableConfigurationProperties(AdminProperties.class)
@EntityScan(basePackages = "com.galaxytasks.model")
public class GalaxytasksApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxytasksApiApplication.class, args);
	}

}
