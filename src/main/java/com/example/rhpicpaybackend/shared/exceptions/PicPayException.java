package com.example.rhpicpaybackend.shared.exceptions;

import org.springframework.http.HttpStatus;

public abstract class PicPayException extends RuntimeException{
  public PicPayException(String message) {
    super(message);
  }

  public PicPayException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract HttpStatus getStatus();
}
