package com.example.rhpicpaybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Funcionario {
    private Long id;
    private String nome;
    private String email;
    private Long telefone;
    private String cargo;
    private String departamento;
    private Double salario;
    private String cidade;
    private Status status;
}
