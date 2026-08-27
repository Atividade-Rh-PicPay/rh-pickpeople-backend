package com.example.rhpicpaybackend.profile.dto.request;

import com.example.rhpicpaybackend.profile.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.profile.dto.groupValidations.doPut;
import com.example.rhpicpaybackend.shared.helpers.ApplicationConstants;
import com.example.rhpicpaybackend.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.*;

public record UpdateMyUserRequestDTO (
    @NotBlank(message = "{validation.name.required}", groups = doPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.name.regex}",
        groups = {doPatch.class, doPut.class}
    )
    String name,

    @NotBlank(message = "{validation.email.required}", groups = doPut.class)
    @Email(
        regexp = RegexPatterns.EMAIL,
        message = "{validation.email.regex}" ,
        groups = {doPatch.class, doPut.class}
    )
    String email,

    @NotBlank(message = "{validation.password.required}", groups = doPut.class)
    @Size(
        min = ApplicationConstants.MIN_PASSWORD_LENGTH,
        max = ApplicationConstants.MAX_PASSWORD_LENGTH,
        message = "{validation.password.size}",
        groups = {doPatch.class, doPut.class}
    )
    @Email(
        regexp = RegexPatterns.PASSWORD,
        message = "{validation.password.regex}",
        groups = {doPatch.class, doPut.class}
    )
    String password,

    @NotBlank(message = "{validation.phone.required}", groups = doPut.class)
    @Pattern(
        regexp = RegexPatterns.PHONE,
        message = "{validation.phone.regex}",
        groups = {doPatch.class, doPut.class}
    )
    String phone,

    @NotBlank(message = "{validation.city.required}", groups = doPut.class)
    @Pattern(
        regexp = RegexPatterns.NAME,
        message = "{validation.city.regex}",
        groups = {doPatch.class, doPut.class}
    )
    String city
){
}
