package com.entreprise.socialnetwork.service;

import com.entreprise.socialnetwork.model.User;
import com.entreprise.socialnetwork.repository.UserRepository;
import com.entreprise.socialnetwork.security.JwtService;
import com.entreprise.socialnetwork.security.dto.AuthResponse;
import com.entreprise.socialnetwork.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email déja utilisé");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new java.util.ArrayList<>()
        );

        return jwtService.generateToken(userDetails);
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new java.util.ArrayList<>()
        );

        return jwtService.generateToken(userDetails);
    }
}
