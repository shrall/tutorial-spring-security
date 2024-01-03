package com.shrall.tutorialspringsecurity.services;

import com.shrall.tutorialspringsecurity.dto.SignupRequest;
import com.shrall.tutorialspringsecurity.entities.User;

public interface AuthService {
    User signup(SignupRequest signupRequest);
}
