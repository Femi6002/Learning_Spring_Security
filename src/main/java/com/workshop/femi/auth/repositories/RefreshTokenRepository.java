package com.workshop.femi.auth.repositories;

import com.workshop.femi.auth.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
