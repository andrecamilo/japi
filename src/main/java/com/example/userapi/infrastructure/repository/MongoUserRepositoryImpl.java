package com.example.userapi.infrastructure.repository;

import com.example.userapi.domain.model.User;
import com.example.userapi.domain.port.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MongoUserRepositoryImpl implements UserRepository {
    private final MongoTemplate mongoTemplate;

    public MongoUserRepositoryImpl(@Lazy MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, User.class));
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
} 