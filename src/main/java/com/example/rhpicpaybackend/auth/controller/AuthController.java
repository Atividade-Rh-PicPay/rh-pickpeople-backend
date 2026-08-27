package com.example.rhpicpaybackend.auth.controller;

import com.example.rhpicpaybackend.auth.dto.input.LoginInputDTO;
import com.example.rhpicpaybackend.auth.dto.input.RefreshTokenInputDTO;
import com.example.rhpicpaybackend.auth.dto.output.LoginOutputDTO;
import com.example.rhpicpaybackend.auth.dto.request.LoginRequestDTO;
import com.example.rhpicpaybackend.auth.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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

  private static final String ACCESS_TOKEN_COOKIE = "access_token";
  private static final String REFRESH_TOKEN_COOKIE = "refresh_token";

  private static final Duration ACCESS_TOKEN_DURATION =
      Duration.ofHours(1);

  private static final Duration REFRESH_TOKEN_DURATION =
      Duration.ofDays(7);

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginOutputDTO> login(
      @RequestBody @Valid LoginRequestDTO input,
      HttpServletResponse response
  ) {

    LoginOutputDTO output = authService.login(
        new LoginInputDTO(input)
    );

    addAccessTokenCookie(
        response,
        output.accessToken()
    );

    addRefreshTokenCookie(
        response,
        output.refreshToken()
    );

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(output);
  }

  @PostMapping("/refresh/{email}")
  public ResponseEntity<LoginOutputDTO> refreshToken(
      @PathVariable
      @Valid
      @Email(message = "{validation.email.regex}")
      String email,

      @CookieValue(
          name = REFRESH_TOKEN_COOKIE,
          required = false
      )
      String refreshToken,

      HttpServletResponse response
  ) {

    if (refreshToken == null || refreshToken.isBlank()) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .build();
    }

    LoginOutputDTO output = authService.refreshToken(
        new RefreshTokenInputDTO(email, refreshToken)
    );

    addAccessTokenCookie(
        response,
        output.accessToken()
    );

    addRefreshTokenCookie(
        response,
        output.refreshToken()
    );

    return ResponseEntity.ok(output);
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(
      HttpServletResponse response
  ) {
    deleteCookie(response, ACCESS_TOKEN_COOKIE, "/");
    deleteCookie(response, REFRESH_TOKEN_COOKIE, "/api/v1/auth/refresh");

    return ResponseEntity.noContent().build();
  }

  private void addAccessTokenCookie(
      HttpServletResponse response,
      String token
  ) {

    ResponseCookie cookie = ResponseCookie
        .from(ACCESS_TOKEN_COOKIE, token)
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path("/")
        .maxAge(ACCESS_TOKEN_DURATION)
        .build();

    response.addHeader(
        HttpHeaders.SET_COOKIE,
        cookie.toString()
    );
  }

  private void addRefreshTokenCookie(
      HttpServletResponse response,
      String token
  ) {

    ResponseCookie cookie = ResponseCookie
        .from(REFRESH_TOKEN_COOKIE, token)
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path("/api/v1/auth/refresh")
        .maxAge(REFRESH_TOKEN_DURATION)
        .build();

    response.addHeader(
        HttpHeaders.SET_COOKIE,
        cookie.toString()
    );
  }

  private void deleteCookie(
      HttpServletResponse response,
      String name,
      String path
  ) {

    ResponseCookie cookie = ResponseCookie
        .from(name, "")
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .path(path)
        .maxAge(Duration.ZERO)
        .build();

    response.addHeader(
        HttpHeaders.SET_COOKIE,
        cookie.toString()
    );
  }
}