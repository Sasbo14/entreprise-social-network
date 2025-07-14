package com.entreprise.socialnetwork.controller;

import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "Utilisateur ajouté avec succès";
    }
}
