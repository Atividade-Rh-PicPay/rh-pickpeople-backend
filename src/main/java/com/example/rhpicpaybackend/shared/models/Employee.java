package com.example.rhpicpaybackend.shared.models;

import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import com.example.rhpicpaybackend.shared.helpers.NormalizeInput;
import com.example.rhpicpaybackend.shared.helpers.NormalizeOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Employee {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String department;
    private Double salary;
    private String city;
    private EmployeeStatusEnum status;
    private LocalDateTime createdAt;

    public Employee(String name, String email, String password, String phone, String role, String department, Double salary, String city, EmployeeStatusEnum status) {
        this.name = NormalizeInput.name(name);
        this.email = NormalizeInput.email(email);
        this.password = password;
        this.phone = NormalizeInput.phone(phone);
        this.role = NormalizeInput.name(role);
        this.department = NormalizeInput.name(department);
        this.salary = salary;
        this.city = NormalizeInput.name(city);
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return NormalizeOutput.name(name);
    }

    public String getEmail() {
        return NormalizeOutput.email(email);
    }

    public String getRole() {
        return NormalizeOutput.name(role);
    }

    public String getDepartment() {
        return NormalizeOutput.name(department);
    }

    public String getPhone(){
        return NormalizeOutput.phone(phone);
    }

    public String getCity() {
        return NormalizeOutput.name(city);
    }
}