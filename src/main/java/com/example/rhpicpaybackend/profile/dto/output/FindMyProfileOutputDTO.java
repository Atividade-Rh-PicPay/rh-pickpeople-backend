package com.example.rhpicpaybackend.profile.dto.output;

public record FindMyProfileOutputDTO (
    Long id,
    String name,
    String email,
    String phone,
    String role,
    String department,
    Double salary,
    String city,
    String status
){
}
