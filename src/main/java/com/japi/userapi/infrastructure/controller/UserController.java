package com.japi.userapi.infrastructure.controller;

import com.japi.userapi.domain.model.User;
import com.japi.userapi.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuários", description = "APIs para gerenciamento de usuários")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/Thread/{id}")
    @Operation(summary = "Excluir usuário")
    public ResponseEntity<Void> deleteUserThread(@PathVariable String id) {
        final java.util.concurrent.SynchronousQueue<User> channel = new java.util.concurrent.SynchronousQueue<>();

        Thread t1 = new Thread(() -> {
            var user = userService.getUserById(id);
            userService.deleteUser(id);
            try {
                channel.put(user.orElse(null));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                User user = channel.take();
                user.setName(user.getName() + " - Updated by Thread 2");
                userService.createUser(user);
                System.out.println("Thread 2 recebeu: " + user);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();

        return ResponseEntity.accepted().build();
    }
}