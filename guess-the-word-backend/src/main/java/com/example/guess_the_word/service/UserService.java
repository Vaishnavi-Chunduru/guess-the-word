package com.example.guess_the_word.service;

import com.example.guess_the_word.dto.RegisterRequest;
import com.example.guess_the_word.entity.Role;
import com.example.guess_the_word.entity.User;
import com.example.guess_the_word.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.guess_the_word.dto.LoginRequest;

import java.time.LocalDateTime;

// For saving the user into the table
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // for sign up
    public User registerUser(RegisterRequest request) {

        // Check whether username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Create new user
        User user = new User();

        user.setUsername(request.getUsername());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Every newly registered user is a PLAYER
        user.setRole(Role.PLAYER);

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    // for login
    public User loginUser(LoginRequest request) {

        // Find user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }
}