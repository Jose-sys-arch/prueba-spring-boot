package com.example.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.prueba")
@EntityScan("com.example.prueba.models") 
@SpringBootApplication
public class PruebaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}
}
