package com.truenorth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TrueNorthPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrueNorthPocApplication.class, args);
	}

}
