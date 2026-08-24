package com.example.rhpicpaybackend.employee.dto.request;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.employee.dto.groupValidations.doPostPut;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "name obrigatório.", groups = doPostPut.class)
    private String name;

    @Email(
            regexp = "[a-z]+\\.[a-z]+@(picpay)\\.com",
            message = "Email deve conter domínio e seguir o seguinte formato: name.sobrename@picpay.com" ,
            groups = {doPostPut.class, doPatch.class})
    @NotBlank(message = "Email obrigatório.", groups = doPostPut.class)
    private String email;

    @NotBlank(message = "phone obrigatório. Deve conter 11 dígitos!", groups = doPostPut.class)
    @Size(min = 11, max = 11, message = "O phone deve conter 11 números.", groups = {doPostPut.class, doPatch.class})
    private String phone;

    @NotBlank(message = "role obrigatório.", groups = doPostPut.class)
    private String role;

    @NotBlank(message = "department obrigatório.", groups = doPostPut.class)
    private String department;

    @NotNull(message = "Salário obrigatório. Deve ser maior um valor positivo!", groups = doPostPut.class)
    @DecimalMin(value = "0.0", message = "Insira um valor positivo", groups = {doPostPut.class, doPatch.class})
    private Double salary;

    @NotBlank(message = "City obrigatória.", groups = doPostPut.class)
    private String city;
}
