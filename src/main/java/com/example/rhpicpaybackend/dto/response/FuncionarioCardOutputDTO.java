package com.example.rhpicpaybackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioCardOutputDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private String departamento;
    private String status;
}
