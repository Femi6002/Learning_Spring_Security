package com.workshop.femi.auth.service;

import com.workshop.femi.auth.models.RefreshToken;
import com.workshop.femi.auth.models.User;
import com.workshop.femi.auth.repositories.RefreshTokenRepository;
import com.workshop.femi.auth.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generateRefreshToken(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: "+ username));
        RefreshToken refreshToken = user.getRefreshToken();
        if (refreshToken == null) {
            long refreshTokenValidity = 24*24*60*60*1000;
            refreshToken = RefreshToken
                    .builder()
                    .refreshToken(UUID
                            .randomUUID()
                            .toString())
                    .expiresAt(Instant
                            .now()
                            .plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();

            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refreshToken1 =  refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(() -> (new RuntimeException("Refresh token not found")));
        if(refreshToken1.getExpiresAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken1);
            throw new RuntimeException("Refresh token expired");
        }
        return refreshToken1;
    }
}
