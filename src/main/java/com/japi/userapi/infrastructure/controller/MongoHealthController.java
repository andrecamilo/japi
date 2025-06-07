package com.japi.userapi.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoHealthController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/health/mongo")
    public ResponseEntity<String> healthCheck() {
        try {
            mongoTemplate.executeCommand("{ ping: 1 }");
            return ResponseEntity.ok("MongoDB is healthy");
        } catch (Exception e) {
            return ResponseEntity.status(503).body("MongoDB is unavailable");
        }
    }
}