package com.example.rhpicpaybackend.shared.security;

import com.example.rhpicpaybackend.shared.exceptions.UnauthorizedException;
import com.example.rhpicpaybackend.shared.helpers.CustomUserDetails;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  public UserService(@Lazy EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    Employee employee =  employeeRepository.findByEmailHired(email).orElseThrow(
        () -> new UnauthorizedException("exception.login.invalid")
    );

    return new CustomUserDetails(
        employee.getEmail(),
        employee.getPassword(),
        employee.getDepartment(),
        employee.getId()
    );
  }
}
