package com.example.rhpicpaybackend.employee.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCardOutputDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String department;
    private String status;
    private LocalDateTime createdAt;
}
