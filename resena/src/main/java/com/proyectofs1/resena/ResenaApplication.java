package com.proyectofs1.resena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // ESTO ES VITAL
public class ResenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResenaApplication.class, args);
	}

}
