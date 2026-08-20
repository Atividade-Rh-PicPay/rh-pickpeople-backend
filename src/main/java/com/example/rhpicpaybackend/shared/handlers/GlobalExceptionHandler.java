package com.example.rhpicpaybackend.shared.handlers;

import com.example.rhpicpaybackend.dto.output.ExceptionOutputDTO;
import com.example.rhpicpaybackend.shared.exceptions.PicPayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  @ExceptionHandler(PicPayException.class)
  public ResponseEntity<ExceptionOutputDTO> handlePicPayException(PicPayException ex){
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            ex.getMessage(),
            ex.getStatus()
        ),
        ex.getStatus()
    );
  }
}
