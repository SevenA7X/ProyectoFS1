package com.proyectofs1.moderacion;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients 
public class ModeracionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModeracionApplication.class, args);
    }
}