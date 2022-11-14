package com.toyproject.springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToyToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyToolApplication.class, args);
		System.out.println("**************************************************");
		System.out.println("------------Welcome to TOY STATION----------------");
		System.out.println("Open the URL: //http://localhost:8080/toysystem");
	}
}
