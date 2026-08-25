package com.example.rhpicpaybackend.auth.service;

import com.example.rhpicpaybackend.auth.dto.input.LoginInputDTO;
import com.example.rhpicpaybackend.auth.dto.input.RefreshTokenInputDTO;
import com.example.rhpicpaybackend.auth.dto.output.LoginOutputDTO;
import com.example.rhpicpaybackend.shared.exceptions.BadRequestException;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider tokenProvider;

  private final PasswordEncoder passwordEncoder;
  private final EmployeeRepository employeeRepository;

  public LoginOutputDTO login(LoginInputDTO input){
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.email(),
            input.password()
        )
    );

    Employee employee = employeeRepository.findByEmail(input.email()).orElseThrow(
        () -> new BadRequestException("exception.login.invalid")
    );

    return tokenProvider.createAccessToken(
        input.email(),
        List.of(employee.getRole())
    );
  }

  public LoginOutputDTO refreshToken(RefreshTokenInputDTO input){
    employeeRepository.findByEmail(input.email()).orElseThrow(
        () -> new BadRequestException("exception.login.invalid")
    );

    return tokenProvider.refreshToken(input.refreshToken());
  }
}