package com.example.rhpicpaybackend.employee.controller;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPostPut;
import com.example.rhpicpaybackend.employee.dto.input.FindManyEmployeesInputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.FindManyEmployeesOutputDTO;
import com.example.rhpicpaybackend.employee.dto.query_params.FindManyEmployeesQueryParamsDTO;
import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping()
    public ResponseEntity<EmployeeOutputDTO> register(
        @Validated(doPostPut.class)
        @RequestBody
        EmployeeRequestDTO input
    ) {
        return new ResponseEntity<>(
            service.register(input),
            HttpStatus.CREATED
        );
    }

    @GetMapping()
    public ResponseEntity<FindManyEmployeesOutputDTO> findMany(
        @Valid
        @ModelAttribute
        FindManyEmployeesQueryParamsDTO query
    ) {
        return new ResponseEntity<>(
            service.findMany(new FindManyEmployeesInputDTO(query)),
            HttpStatus.OK
        );
    }
}
