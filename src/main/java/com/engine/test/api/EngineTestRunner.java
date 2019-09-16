package com.engine.test.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EngineTestRunner extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EngineTestRunner.class, args);

	}

}
