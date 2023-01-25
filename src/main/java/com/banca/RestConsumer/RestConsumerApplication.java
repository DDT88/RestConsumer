package com.banca.RestConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class RestConsumerApplication {

	private static final Logger LOGGER = LogManager.getLogger(RestConsumerApplication.class);
	

	
	public static void main(String[] args) {
		
		SpringApplication.run(RestConsumerApplication.class, args);
		LOGGER.info("Applicazione Avviata");
	}
}
