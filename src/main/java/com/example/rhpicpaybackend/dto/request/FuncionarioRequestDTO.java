package com.example.rhpicpaybackend.dto.request;

import com.example.rhpicpaybackend.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.dto.groupValidations.doPostPut;
import com.example.rhpicpaybackend.model.Status;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO {

    @NotBlank(message = "Nome obrigatório.", groups = doPostPut.class)
    private String nome;

    @Email(
            regexp = "[a-z]+\\.[a-zA-Z]@(picpay)\\.com",
            message = "Email deve conter domínio e seguir o seguinte formato: nome.sobrenome@picpay.com" ,
            groups = {doPostPut.class, doPatch.class})
    @NotBlank(message = "Email obrigatório.", groups = doPostPut.class)
    private String email;

    @NotBlank(message = "Telefone obrigatório. Deve conter 11 dígitos!", groups = doPostPut.class)
    @Size(min = 11, max = 11, message = "O telefone deve conter 11 números.", groups = {doPostPut.class, doPatch.class})
    private String telefone;

    @NotBlank(message = "Cargo obrigatório.", groups = doPostPut.class)
    private String cargo;

    @NotBlank(message = "Departamento obrigatório.", groups = doPostPut.class)
    private String departamento;

    @NotNull(message = "Salário obrigatório. Deve ser maior um valor positivo!", groups = doPostPut.class)
    @DecimalMin(value = "0.0", message = "Insira um valor positivo", groups = {doPostPut.class, doPatch.class})
    private Double salario;

    @NotBlank(message = "Cidade obrigatória.", groups = doPostPut.class)
    private String cidade;
}
