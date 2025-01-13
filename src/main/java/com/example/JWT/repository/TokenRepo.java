package com.example.JWT.repository;

import com.example.JWT.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    @Query("""
            SELECT t FROM Token t INNER JOIN  User u
            on t.user.id = u.id
            where t.user.id = :userId and t.loggedOut = false 
            """)

    List<Token> findAllAccessTokenByUser(Long userId);
    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByRefreshToken(String refreshToken);
}
