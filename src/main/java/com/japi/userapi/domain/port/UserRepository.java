package com.japi.userapi.domain.port;

import com.japi.userapi.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(String id);
    void deleteById(String id);
} 