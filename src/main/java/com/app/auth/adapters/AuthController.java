package com.app.auth.adapters;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.app.auth.infrastructure.dto.LoginRequest;
import com.app.auth.infrastructure.dto.RegisterRequest;

import com.app.shared.adapters.BaseController;

import com.app.auth.application.AuthService;

import com.app.auth.domain.AuthResponse;
import com.app.auth.domain.ProfileResponse;
import com.app.auth.domain.RefreshTokenRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return handleRequest(() -> authService.login(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return handleRequest(() -> authService.register(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return handleRequest(() -> authService.refreshToken(request.getRefreshToken()));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getUserProfile(Authentication authentication) {
        return handleRequest(() -> authService.getProfile(authentication.getName()));
    }
}