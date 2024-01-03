package com.shrall.tutorialspringsecurity.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shrall.tutorialspringsecurity.dto.SignupRequest;
import com.shrall.tutorialspringsecurity.entities.Role;
import com.shrall.tutorialspringsecurity.entities.User;
import com.shrall.tutorialspringsecurity.repositories.UserRepository;
import com.shrall.tutorialspringsecurity.services.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }
}
