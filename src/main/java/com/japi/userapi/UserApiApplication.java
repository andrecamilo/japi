package com.japi.userapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class UserApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner mongoHealthCheck(MongoTemplate mongoTemplate) {
        return args -> {
            try {
                mongoTemplate.executeCommand("{ ping: 1 }");
                System.out.println("[HEALTHCHECK] MongoDB is healthy");
            } catch (Exception e) {
                System.err.println("[HEALTHCHECK] MongoDB is unavailable: " + e.getMessage());
            }
        };
    }
}