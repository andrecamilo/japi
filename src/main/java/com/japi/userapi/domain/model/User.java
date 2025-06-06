package com.japi.userapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    @Id
    private String id;
    private String name;
    private String email;
} 