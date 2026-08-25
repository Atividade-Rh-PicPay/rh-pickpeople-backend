package com.example.rhpicpaybackend.shared.security;

import com.example.rhpicpaybackend.shared.exceptions.UnauthorizedException;
import com.example.rhpicpaybackend.shared.helpers.CustomUserDetails;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;
  private final MessageService messageService;

  @Override
  public UserDetails loadUserByUsername(String email) {
    Employee employee =  employeeRepository.findByEmail(email).orElseThrow(
        () -> new UnauthorizedException(messageService.getMessage("exception.login.invalid"))
    );

    return new CustomUserDetails(
        employee.getEmail(),
        employee.getPassword(),
        employee.getRole(),
        employee.getId()
    );
  }
}
