package com.entreprise.socialnetwork.controller;


import com.entreprise.socialnetwork.entity.User;
import com.entreprise.socialnetwork.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. Enregistrer un nouvel utilisateur
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("Received user:");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Firstname: " + user.getFirstname());
        System.out.println("Lastname: " + user.getLastname());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());


        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
