package com.shrall.tutorialspringsecurity.services;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    boolean validateToken(String token, UserDetails userDetails);

}
