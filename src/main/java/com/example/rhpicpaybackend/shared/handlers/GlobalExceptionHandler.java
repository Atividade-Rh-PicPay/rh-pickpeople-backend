package com.example.rhpicpaybackend.shared.handlers;

import com.example.rhpicpaybackend.employee.dto.output.ExceptionOutputDTO;
import com.example.rhpicpaybackend.shared.exceptions.PicPayException;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final MessageService messageService;

  @ExceptionHandler(PicPayException.class)
  public ResponseEntity<ExceptionOutputDTO> handlePicPayException(PicPayException ex){
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            messageService.getMessage(ex.getMessage()),
            ex.getStatus()
        ),
        ex.getStatus()
    );
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ExceptionOutputDTO> handleNoResourceFoundException(NoResourceFoundException ex){
    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            messageService.getMessage("exception.route.not-found"),
            HttpStatus.NOT_FOUND
        ),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionOutputDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(f -> f.getDefaultMessage())
        .toList();

    return new ResponseEntity<>(
        new ExceptionOutputDTO(
            String.join("\n", errors),
            HttpStatus.BAD_REQUEST
        ),
        HttpStatus.BAD_REQUEST
    );
  }
}
