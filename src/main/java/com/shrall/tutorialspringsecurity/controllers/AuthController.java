package com.shrall.tutorialspringsecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shrall.tutorialspringsecurity.dto.JWTAuthResponse;
import com.shrall.tutorialspringsecurity.dto.SigninRequest;
import com.shrall.tutorialspringsecurity.dto.SignupRequest;
import com.shrall.tutorialspringsecurity.entities.User;
import com.shrall.tutorialspringsecurity.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
}
