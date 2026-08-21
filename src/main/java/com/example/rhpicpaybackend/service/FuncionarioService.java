package com.example.rhpicpaybackend.service;

import com.example.rhpicpaybackend.dto.request.FuncionarioRequestDTO;
import com.example.rhpicpaybackend.model.Funcionario;
import com.example.rhpicpaybackend.model.Status;
import com.example.rhpicpaybackend.repository.FuncionarioRepository;
import com.example.rhpicpaybackend.utils.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public ResponseEntity<String> cadastrar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario(
                IdUtil.gerarId(repository.getFuncionarios()),
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone(),
                dto.getCargo(),
                dto.getDepartamento(),
                dto.getSalario(),
                dto.getCidade(),
                Status.EM_ANALISE
        );

        repository.save(funcionario);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Funcionário registrado no sistema com sucesso! Status atual: Em análise.");
    }
}
