package com.shrall.tutorialspringsecurity.services.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shrall.tutorialspringsecurity.dto.JWTAuthResponse;
import com.shrall.tutorialspringsecurity.dto.RefreshTokenRequest;
import com.shrall.tutorialspringsecurity.dto.SigninRequest;
import com.shrall.tutorialspringsecurity.dto.SignupRequest;
import com.shrall.tutorialspringsecurity.entities.Role;
import com.shrall.tutorialspringsecurity.entities.User;
import com.shrall.tutorialspringsecurity.repositories.UserRepository;
import com.shrall.tutorialspringsecurity.services.AuthService;
import com.shrall.tutorialspringsecurity.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }

    public JWTAuthResponse signin(SigninRequest signinRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();

        jwtAuthResponse.setToken(token);
        jwtAuthResponse.setRefreshToken(refreshToken);
        jwtAuthResponse.setEmail(user.getEmail());
        jwtAuthResponse.setRole(user.getRole().name());

        return jwtAuthResponse;
    }

    public JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("E-Mail not found"));

        if (jwtService.validateToken(refreshTokenRequest.getToken(), user)) {
            var token = jwtService.generateToken(user);

            JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();

            jwtAuthResponse.setToken(token);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            jwtAuthResponse.setEmail(user.getEmail());
            jwtAuthResponse.setRole(user.getRole().name());

            return jwtAuthResponse;
        }
        return null;
    }
}
