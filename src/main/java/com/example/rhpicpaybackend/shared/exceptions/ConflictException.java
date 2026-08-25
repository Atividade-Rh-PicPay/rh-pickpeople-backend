package com.example.rhpicpaybackend.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends PicPayException{
  public ConflictException(String message) {
    super(message);
  }

  public ConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public HttpStatus getStatus() {
    return HttpStatus.CONFLICT;
  }
}
