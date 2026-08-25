package com.example.rhpicpaybackend.auth.dto.input;

public record RefreshTokenInputDTO(
    String email,
    String refreshToken
) {
}
