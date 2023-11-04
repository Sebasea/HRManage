package com.hrManagement;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Generated
@SpringBootApplication
@EnableWebSecurity
public class HrmanamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmanamentApplication.class, args);
	}

}
