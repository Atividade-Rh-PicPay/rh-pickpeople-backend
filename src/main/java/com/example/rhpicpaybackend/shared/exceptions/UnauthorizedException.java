package com.example.rhpicpaybackend.shared.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends PicPayException {
  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.UNAUTHORIZED;
  }
}
