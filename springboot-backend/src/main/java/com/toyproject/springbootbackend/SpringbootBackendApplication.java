package com.toyproject.springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
		System.out.println("Welcome to TOY STATION");
		System.out.println("Open the URL: //http://localhost:8080/toysystem");
	}

	//http://localhost:8080/Project/api/v1
}
