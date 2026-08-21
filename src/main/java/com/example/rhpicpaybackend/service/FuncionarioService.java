package com.example.rhpicpaybackend.service;

import com.example.rhpicpaybackend.dto.output.FuncionarioCardOutputDTO;
import com.example.rhpicpaybackend.dto.request.FuncionarioRequestDTO;
import com.example.rhpicpaybackend.model.Funcionario;
import com.example.rhpicpaybackend.repository.FuncionarioRepository;
import com.example.rhpicpaybackend.shared.enums.FuncionarioStatusEnum;
import com.example.rhpicpaybackend.utils.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
                FuncionarioStatusEnum.EM_ANALISE
        );

        repository.save(funcionario);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Funcionário registrado no sistema com sucesso! Status atual: Em análise.");
    }

    public ResponseEntity<List<FuncionarioCardOutputDTO>> listarUsuarios() {
        List<Funcionario> funcionarios = repository.findAll();

        if (funcionarios.isEmpty()) {
            // lança exceção
        }

        List<FuncionarioCardOutputDTO> funcionariosDto = funcionarios.stream()
                .map(funcionario -> new FuncionarioCardOutputDTO(
                        funcionario.getId(),
                        funcionario.getNome(),
                        funcionario.getEmail(),
                        funcionario.getCargo(),
                        funcionario.getDepartamento(),
                        funcionario.getStatus().getNome()
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(funcionariosDto);
    }
}
