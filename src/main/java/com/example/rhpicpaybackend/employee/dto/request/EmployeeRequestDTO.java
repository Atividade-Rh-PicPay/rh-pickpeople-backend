package com.example.rhpicpaybackend.employee.dto.request;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.employee.dto.groupValidations.doPostPut;
import com.example.rhpicpaybackend.shared.helpers.ApplicationConstants;
import com.example.rhpicpaybackend.shared.helpers.RegexPatterns;
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
    @NotBlank(message = "{validation.name.required}", groups = doPostPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.name.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String name;

    @NotBlank(message = "{validation.email.required}", groups = doPostPut.class)
    @Email(
            regexp = RegexPatterns.EMAIL,
            message = "{validation.email.regex}" ,
            groups = {doPostPut.class, doPatch.class})
    private String email;

    @NotBlank(message = "{validation.password.required}", groups = doPostPut.class)
    @Size(
        min = ApplicationConstants.MIN_PASSWORD_LENGTH,
        max = ApplicationConstants.MAX_PASSWORD_LENGTH,
        message = "{validation.password.size}"
    )
    @Email(
        regexp = RegexPatterns.PASSWORD,
        message = "{validation.password.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String password;

    @NotBlank(message = "{validation.phone.required}", groups = doPostPut.class)
    @Pattern(
        regexp = RegexPatterns.PHONE,
        message = "{validation.phone.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String phone;

    @NotBlank(message = "{validation.role.required}", groups = doPostPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.role.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String role;

    @NotBlank(message = "{validation.department.required}", groups = doPostPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.department.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String department;

    @NotNull(message = "{validation.salary.required}", groups = doPostPut.class)
    @DecimalMin(value = "0.0", message = "{validation.salary.min-value}", groups = {doPostPut.class, doPatch.class})
    private Double salary;

    @NotBlank(message = "{validation.city.required}", groups = doPostPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.city.regex}",
        groups = {doPostPut.class, doPatch.class}
    )
    private String city;
}
