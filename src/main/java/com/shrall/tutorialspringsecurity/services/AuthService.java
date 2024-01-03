package com.shrall.tutorialspringsecurity.services;

import com.shrall.tutorialspringsecurity.dto.JWTAuthResponse;
import com.shrall.tutorialspringsecurity.dto.RefreshTokenRequest;
import com.shrall.tutorialspringsecurity.dto.SigninRequest;
import com.shrall.tutorialspringsecurity.dto.SignupRequest;
import com.shrall.tutorialspringsecurity.entities.User;

public interface AuthService {
    User signup(SignupRequest signupRequest);

    JWTAuthResponse signin(SigninRequest signinRequest);

    JWTAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
