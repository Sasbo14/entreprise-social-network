package com.entreprise.socialnetwork.service;

import java.util.Optional;
import com.entreprise.socialnetwork.entity.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}