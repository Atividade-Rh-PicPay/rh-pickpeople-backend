package com.example.rhpicpaybackend.auth.dto.input;

import com.example.rhpicpaybackend.auth.dto.request.LoginRequestDTO;

public record LoginInputDTO(
    String email,
    String password
) {
  public LoginInputDTO(LoginRequestDTO input) {
    this(
        input.email(),
        input.password()
    );
  }
}
