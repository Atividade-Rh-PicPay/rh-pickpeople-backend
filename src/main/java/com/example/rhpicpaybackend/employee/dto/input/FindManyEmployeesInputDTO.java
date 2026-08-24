package com.example.rhpicpaybackend.employee.dto.input;

import com.example.rhpicpaybackend.employee.dto.query_params.FindManyEmployeesQueryParamsDTO;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;

public record FindManyEmployeesInputDTO(
    String name,
    String email,
    String role,
    EmployeeStatusEnum status,
    Integer take,
    Integer skip
) {
  public FindManyEmployeesInputDTO(FindManyEmployeesQueryParamsDTO input) {
    this(
        input.name(),
        input.email(),
        input.role(),
        input.status() != null
            ? EmployeeStatusEnum.fromId(input.status())
            : null,
        input.take(),
        input.skip()
    );
  }
}
