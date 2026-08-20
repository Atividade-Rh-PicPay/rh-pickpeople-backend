package com.example.rhpicpaybackend.repository;

import com.example.rhpicpaybackend.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FuncionarioRepository {

    private List<Funcionario> funcionarios= new ArrayList<>();

}
