package com.example.rhpicpaybackend.profile.dto.input;

import com.example.rhpicpaybackend.profile.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.profile.dto.groupValidations.doPut;
import com.example.rhpicpaybackend.profile.dto.request.UpdateMyUserRequestDTO;
import com.example.rhpicpaybackend.shared.helpers.ApplicationConstants;
import com.example.rhpicpaybackend.shared.helpers.NormalizeInput;
import com.example.rhpicpaybackend.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateMyUserInputDTO(
    Long id,
    String name,
    String email,
    String password,
    String phone,
    String city
){
  public UpdateMyUserInputDTO (Long id, UpdateMyUserRequestDTO input){
    this(
        id,
        NormalizeInput.name(input.name()),
        NormalizeInput.email(input.email()),
        NormalizeInput.password(input.password()),
        input.phone(),
        input.city()
    );
  }
}
