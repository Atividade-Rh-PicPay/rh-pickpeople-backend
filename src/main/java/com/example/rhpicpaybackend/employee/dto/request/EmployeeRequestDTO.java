package com.example.rhpicpaybackend.employee.dto.request;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.employee.dto.groupValidations.doPost;
import com.example.rhpicpaybackend.employee.dto.groupValidations.doPut;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
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
    // TO DO: refactor the message of the exceptions to a message's param

    @NotBlank(message = "Name requested.", groups = {doPost.class, doPut.class})
    private String name;

    @Email(
            regexp = "[a-z]+\\.[a-z]+@(picpay)\\.com",
            message = "Email must follow the domain format: name.lastname@picpay.com" ,
            groups = {doPost.class, doPatch.class})
    @NotBlank(message = "Email requested..", groups = {doPost.class, doPut.class})
    private String email;

    @NotBlank(message = "Phone requested. Must contains 11 numbers!", groups = {doPost.class, doPut.class})
    @Size(min = 11, max = 11, message = "O phone deve conter 11 números.", groups = {doPost.class, doPut.class, doPatch.class})
    private String phone;

    @NotBlank(message = "Role requested.", groups = {doPost.class, doPut.class})
    private String role;

    @NotBlank(message = "Departament requested.", groups = {doPost.class, doPut.class})
    private String department;

    @NotNull(message = "Salary requested. Must be a positive value!", groups = {doPost.class, doPut.class})
    @DecimalMin(value = "0.0", message = "Must be a positive value.", groups = {doPost.class, doPut.class, doPatch.class})
    private Double salary;

    @NotBlank(message = "City requested.", groups = {doPost.class, doPut.class})
    private String city;

    @NotNull(message = "Status requested.", groups = doPut.class)
    private Integer status;
}
