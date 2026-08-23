package com.example.rhpicpaybackend.repository;

import com.example.rhpicpaybackend.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Component
public class FuncionarioRepository {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void save(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> findAll() {
        return funcionarios;
    }

}
