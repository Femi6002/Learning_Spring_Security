package com.workshop.femi.auth.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false, unique = true, updatable = false, length = 50)
    private String refreshToken;

    @Column(nullable = false)
    private Instant expiresAt;

    @OneToOne
    private User user;

}
