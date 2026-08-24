package com.example.rhpicpaybackend.shared.model;

import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String department;
    private Double salary;
    private String city;
    private EmployeeStatusEnum status;

    public Employee(String name, String email, String phone, String role, String department, Double salary, String city, EmployeeStatusEnum status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.department = department;
        this.salary = salary;
        this.city = city;
        this.status = status;
    }
}