package com.example.rhpicpaybackend.employee.dto.output;

import java.util.List;

public record FindManyEmployeesOutputDTO(
    List<EmployeeCardOutputDTO> employees,
    Integer totalCount
) {
}
