package com.capgemini.cab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/* describes the class as Base class
 * It’s same as declaring a class with @Configuration, @EnableAutoConfiguration and @ComponentScan annotations
 * @Configuration indicates that a class declares one or more @Bean methods and by the Spring container to generate 
 *                bean definitions and service requests for those beans at runtime
 * @EnableAutoConfiguration automatically configures the Spring application based on its included jar files, 
 *                it sets up defaults or helper based on dependencies in pom.xml
 * @ComponentScan used to specify base packages and base package classes
 */

public class OnlineCabBookingApplication {

	public static void main(String[] args) {
		/* run is static that can be used to run a SpringApplication
		 * returns the running ApplicationContext
		 */
		SpringApplication.run(OnlineCabBookingApplication.class, args);
	}

}
