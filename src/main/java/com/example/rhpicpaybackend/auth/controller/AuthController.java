package com.example.rhpicpaybackend.auth.controller;

import com.example.rhpicpaybackend.auth.dto.input.LoginInputDTO;
import com.example.rhpicpaybackend.auth.dto.input.RefreshTokenInputDTO;
import com.example.rhpicpaybackend.auth.dto.output.LoginOutputDTO;
import com.example.rhpicpaybackend.auth.dto.request.LoginRequestDTO;
import com.example.rhpicpaybackend.auth.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
      @RequestBody
      @Valid
      LoginRequestDTO input
  ){
    return new ResponseEntity<>(
        this.authService.login(
            new LoginInputDTO(input)
        ),
        HttpStatus.OK
    );
  }

  @PutMapping("/refresh/{email}")
  public ResponseEntity<LoginOutputDTO> refreshToken(
      @PathVariable
      @Valid
      @Email(message = "{validation.email.regex}")
      String email,

      @RequestHeader("Authorization")
      @NotEmpty(message = "{validation.refresh-token.required}")
      String refreshToken
  ){
    return new ResponseEntity<>(
        this.authService.refreshToken(
            new RefreshTokenInputDTO(email, refreshToken)
        ),
        HttpStatus.OK
    );
  }
}
