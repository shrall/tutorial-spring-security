package com.shrall.tutorialspringsecurity.dto;

import lombok.Data;

@Data
public class JWTAuthResponse {
    private String email;
    private String role;
    private String type = "Bearer";
    private String token;
    private String refreshToken;
}
