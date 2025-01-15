package com.example.JWT.service.impl;

import com.example.JWT.dto.RegistrationRequestDto;
import com.example.JWT.models.Role;
import com.example.JWT.models.Token;
import com.example.JWT.models.User;
import com.example.JWT.repository.TokenRepo;
import com.example.JWT.repository.UserRepo;
import com.example.JWT.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepo tokenRepo;

    public AuthenticationServiceImpl(UserRepo userRepo, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenRepo = tokenRepo;
    }

    public void registr(RegistrationRequestDto request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        user = userRepo.save(user);
    }

    public void revokeAllToken(User user) {
        List<Token> validToken = tokenRepo.findAllAccessTokenByUser(user.getId());

        if (!validToken.isEmpty()) {
            validToken.forEach(t -> {
                t.setLoggedOut(true);
            });
        }
        tokenRepo.saveAll(validToken);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);

        tokenRepo.save(token);
    }
}
