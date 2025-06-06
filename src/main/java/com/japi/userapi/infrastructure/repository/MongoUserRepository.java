package com.japi.userapi.infrastructure.repository;

import com.japi.userapi.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
} 