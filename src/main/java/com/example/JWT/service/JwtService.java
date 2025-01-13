package com.example.JWT.service;

import com.example.JWT.repository.TokenRepo;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${security.jwt.secret_key}")
    private String secretKey;
    @Value("${security.jwt.access_token_expiration}")
    private long accessTokenExpiration;
    @Value("${security.jwt.access_token_expiration}")
    private  long refreshTokenExpiration;

    private final TokenRepo tokenRepo;

    public JwtService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }
    private SecretKey getSgningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

