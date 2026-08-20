package com.example.rhpicpaybackend.dto.output;

import org.springframework.http.HttpStatus;

public record ExceptionOutputDTO(
    String mensagem,
    HttpStatus status
) {
}
