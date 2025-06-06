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
        
        final java.util.concurrent.SynchronousQueue<String> channel = new java.util.concurrent.SynchronousQueue<>();

        Thread t1 = new Thread(() -> {
            userService.deleteUser(id);
            try {
                channel.put("Thread 1 finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                String msg = channel.take();

                if (msg == "Thread 1 finished") {
                    userService.createUser(new User("User from Thread 2","999@999.com"));
                    return;
                }
                // Aqui você pode fazer algo com a mensagem recebida da thread 1
                System.out.println("Thread 2 recebeu: " + msg);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();

        return ResponseEntity.accepted().build();
    }
}