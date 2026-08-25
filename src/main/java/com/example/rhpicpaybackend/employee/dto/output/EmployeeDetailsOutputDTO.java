package com.example.rhpicpaybackend.employee.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsOutputDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String department;
    private Double salary;
    private String city;
    private String status;
}
