package com.example.rhpicpaybackend.controller;

import com.example.rhpicpaybackend.dto.groupValidations.doPostPut;
import com.example.rhpicpaybackend.dto.request.FuncionarioRequestDTO;
import com.example.rhpicpaybackend.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ap1/v1")
@AllArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping("/funcionarios")
    public ResponseEntity<String> cadastrar(@Validated(doPostPut.class) @RequestBody FuncionarioRequestDTO dto) {
        return service.cadastrar(dto);
    }
}
