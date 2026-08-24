package com.example.rhpicpaybackend.employee.dto.query_params;

public record FindManyEmployeesQueryParamsDTO (
    String name,
    String email,
    String role,
    Integer status,
    Integer take,
    Integer skip
){
}
