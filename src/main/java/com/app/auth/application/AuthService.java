package com.app.auth.application;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.auth.infrastructure.JwtUtils;
import com.app.auth.infrastructure.dto.RegisterRequest;

import com.app.shared.application.exception.HandlerException;

import com.app.auth.domain.AuthResponse;
import com.app.auth.domain.IJwtTokenRepository;
import com.app.auth.domain.JwtToken;
import com.app.auth.domain.ProfileResponse;

import com.app.users.domain.IUserRepository;
import com.app.users.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private static final String AUTH_ERROR = "Error de autenticación: %s";
    private static final String TOKEN_ERROR = "Error con el token: %s";

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final IUserRepository userRepo;
    private final IJwtTokenRepository tokenRepo;
    private final PasswordEncoder encoder;

    public AuthResponse login(String email, String password) {
        try {
            return generateAuthResponse(authenticate(email, password), email);
        } catch (BadCredentialsException e) {
            throw new HandlerException(String.format(AUTH_ERROR, "Credenciales inválidas"), "AUTH_ERROR");
        }
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new HandlerException(String.format(AUTH_ERROR, "Email ya registrado"), "VALIDATION_ERROR");
        }

        userRepo.save(new User(
            null,
            request.getName(),
            request.getEmail(),
            encoder.encode(request.getPassword())
        ));

        try {
            return generateAuthResponse(authenticate(request.getEmail(), request.getPassword()), request.getEmail());
        } catch (BadCredentialsException e) {
            throw new HandlerException(String.format(AUTH_ERROR, "Error en autenticación post-registro"), "AUTH_ERROR");
        }
    }

    @Transactional
    public AuthResponse refreshToken(String refreshToken) {
        return tokenRepo.findByToken(refreshToken)
            .map(this::processRefreshToken)
            .orElseThrow(() -> new HandlerException(String.format(TOKEN_ERROR, "Token no encontrado"), "TOKEN_ERROR"));
    }

    @Transactional
    public ProfileResponse getProfile(String email) {
        return userRepo.findByEmail(email)
            .map(user -> new ProfileResponse(user.getId(), user.getName(), user.getEmail()))
            .orElseThrow(() -> new HandlerException(String.format(AUTH_ERROR, "Usuario no encontrado"), "USER_ERROR"));
    }

    private Authentication authenticate(String email, String password) {
        return authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    private AuthResponse generateAuthResponse(Authentication auth, String email) {
        return new AuthResponse(
            jwtUtils.generateToken(auth),
            createRefreshToken(email)
        );
    }

    private AuthResponse processRefreshToken(JwtToken token) {
        if (token.isExpired()) {
            tokenRepo.delete(token);
            throw new HandlerException(String.format(TOKEN_ERROR, "Token expirado"), "TOKEN_ERROR");
        }
        return new AuthResponse(jwtUtils.generateToken(token.getUserEmail()), token.getToken());
    }

    @Transactional
    private String createRefreshToken(String email) {
        tokenRepo.deleteByUserEmail(email);
        return tokenRepo.save(JwtToken.builder()
            .userEmail(email)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusMillis(jwtUtils.getRefreshTokenDurationMs()))
            .build())
            .getToken();
    }
} 