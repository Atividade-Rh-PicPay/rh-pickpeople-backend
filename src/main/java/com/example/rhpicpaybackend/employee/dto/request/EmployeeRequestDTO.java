package com.example.rhpicpaybackend.employee.dto.request;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.shared.helpers.ApplicationConstants;
import com.example.rhpicpaybackend.shared.helpers.RegexPatterns;
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
    @NotBlank(message = "{validation.name.required}", groups = doPost.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.name.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String name;

    @NotBlank(message = "{validation.email.required}", groups = doPost.class)
    @Email(
            regexp = RegexPatterns.EMAIL,
            message = "{validation.email.regex}" ,
            groups = {doPost.class, doPatch.class, doPut.class})
    private String email;

    @NotBlank(message = "{validation.password.required}", groups = doPost.class)
    @Size(
        min = ApplicationConstants.MIN_PASSWORD_LENGTH,
        max = ApplicationConstants.MAX_PASSWORD_LENGTH,
        message = "{validation.password.size}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    @Pattern(
        regexp = RegexPatterns.PASSWORD,
        message = "{validation.password.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String password;

    @NotBlank(message = "{validation.phone.required}", groups = doPost.class)
    @Pattern(
        regexp = RegexPatterns.PHONE,
        message = "{validation.phone.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String phone;

    @NotBlank(message = "{validation.role.required}", groups = doPost.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.role.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String role;

    @NotBlank(message = "{validation.department.required}", groups = doPost.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.department.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String department;

    @NotNull(message = "{validation.salary.required}", groups = doPost.class)
    @DecimalMin(value = "0.1", message = "{validation.salary.min-value}", groups = {doPost.class, doPatch.class})
    private Double salary;

    @NotBlank(message = "{validation.city.required}", groups = doPost.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.city.regex}",
        groups = {doPost.class, doPatch.class, doPut.class}
    )
    private String city;

    @NotNull(message = "{validation.status.required}", groups = {doPut.class})
    private Integer status;
}
