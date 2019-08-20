package com.revature.cmsforce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main thread for content-service found here. 
 * @author 190617jta
 *
 */
@SpringBootApplication
@EnableJpaRepositories("com.revature.repositories")
@EntityScan("com.revature.entities")
@ComponentScan("com.revature")
@EnableEurekaClient
@EnableAutoConfiguration
public class CMSforceApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(CMSforceApplication.class, args);
		
	
		
	}

}
