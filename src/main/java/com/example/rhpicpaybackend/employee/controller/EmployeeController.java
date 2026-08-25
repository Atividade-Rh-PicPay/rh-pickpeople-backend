package com.example.rhpicpaybackend.employee.controller;

import com.example.rhpicpaybackend.employee.dto.groupValidations.doPatch;
import com.example.rhpicpaybackend.employee.dto.groupValidations.doPost;
import com.example.rhpicpaybackend.employee.dto.input.FindManyEmployeesInputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeCardOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeDetailsOutputDTO;
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
    public ResponseEntity<EmployeeCardOutputDTO> register(
        @Validated(doPost.class)
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

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsOutputDTO> findOne(
            @PathVariable(name = "id")
            Long id
    ) {
        return new ResponseEntity<>(
                service.findOne(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDetailsOutputDTO> fullUpdate(
            @PathVariable(name = "id")
            Long id,
            @Validated(doPost.class)
            @RequestBody
            EmployeeRequestDTO input
    ) {
        return new ResponseEntity<>(
                service.fullUpdate(id, input),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDetailsOutputDTO> partialUpdate(
            @PathVariable(name = "id")
            Long id,
            @Validated(doPatch.class)
            @RequestBody
            EmployeeRequestDTO input
    ) {
        return new ResponseEntity<>(
                service.partialUpdate(id, input),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(
            @PathVariable(name = "id")
            Long id
    ) {
        String employeeName = service.deleteOne(id);
        String message = String.format("%s deleted with sucess!", employeeName);

        return new ResponseEntity<>(
                message,
                HttpStatus.OK
        );
    }
}
