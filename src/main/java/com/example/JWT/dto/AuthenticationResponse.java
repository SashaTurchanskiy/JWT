package com.example.JWT.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponse {

    public final String accessToken;
    public final String refreshToken;

    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
