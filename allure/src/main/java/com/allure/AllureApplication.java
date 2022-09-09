package com.allure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AllureApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllureApplication.class, args);
	}

}
