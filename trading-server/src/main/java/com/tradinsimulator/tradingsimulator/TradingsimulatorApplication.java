package com.tradinsimulator.tradingsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class TradingsimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingsimulatorApplication.class, args);
	}

}
