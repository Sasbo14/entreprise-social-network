package com.entreprise.socialnetwork.controller;


import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.security.dto.AuthResponse;
import com.entreprise.socialnetwork.security.dto.LoginRequest;
import com.entreprise.socialnetwork.security.dto.RegisterRequest;
import com.entreprise.socialnetwork.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        String token = authService.register(request);
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return new AuthResponse(token);
    }

}
