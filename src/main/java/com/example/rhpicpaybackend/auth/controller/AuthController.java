package com.example.rhpicpaybackend.auth.controller;

import com.example.rhpicpaybackend.auth.dto.input.LoginInputDTO;
import com.example.rhpicpaybackend.auth.dto.input.RefreshTokenInputDTO;
import com.example.rhpicpaybackend.auth.dto.output.LoginOutputDTO;
import com.example.rhpicpaybackend.auth.dto.request.LoginRequestDTO;
import com.example.rhpicpaybackend.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
      @RequestBody
      @Valid
      LoginRequestDTO input,

      HttpServletResponse response
  ){
    LoginOutputDTO output = this.authService.login(
        new LoginInputDTO(input)
    );

    ResponseCookie cookie = ResponseCookie.from(
            "access_token",
            output.accessToken()
        )
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path("/")
        .maxAge(Duration.ofHours(1))
        .build();

    response.addHeader(
        HttpHeaders.SET_COOKIE,
        cookie.toString()
    );

    return new ResponseEntity<>(
        output,
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
      String refreshToken,

      HttpServletResponse response
  ){
    LoginOutputDTO output = this.authService.refreshToken(
        new RefreshTokenInputDTO(email, refreshToken)
    );

    ResponseCookie cookie = ResponseCookie.from(
            "access_token",
            output.accessToken()
        )
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path("/")
        .maxAge(Duration.ofHours(1))
        .build();

    response.addHeader(
        HttpHeaders.SET_COOKIE,
        cookie.toString()
    );

    return new ResponseEntity<>(
        output,
        HttpStatus.OK
    );
  }
}
