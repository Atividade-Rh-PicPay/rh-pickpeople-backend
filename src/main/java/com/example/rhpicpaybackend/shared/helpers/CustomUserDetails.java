package com.example.rhpicpaybackend.shared.helpers;


import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.List;

@Getter
public class CustomUserDetails extends User {

  private final Long id;

  public CustomUserDetails(
      String username,
      @Nullable String password,
      String department,
      Long id
  ) {
    super(
        username,
        password,
        List.of(new SimpleGrantedAuthority("ROLE_" + department))
    );

    this.id = id;
  }
}