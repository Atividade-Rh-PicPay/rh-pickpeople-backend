package com.example.rhpicpaybackend.employee.service;

import com.example.rhpicpaybackend.employee.dto.input.FindManyEmployeesInputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.FindManyEmployeesOutputDTO;
import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.shared.exceptions.ConflictException;
import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final MessageService messageService;

    public EmployeeOutputDTO register(EmployeeRequestDTO input) {
        if (repository.findByEmail(input.getEmail()).isPresent()) throw new ConflictException(messageService.getMessage("exception.employee-email.conflict"));

        Employee employee = new Employee(
                input.getName(),
                input.getEmail(),
                input.getPhone(),
                input.getRole(),
                input.getDepartment(),
                input.getSalary(),
                input.getCity(),
                EmployeeStatusEnum.UNDER_REVIEW
        );

        repository.save(employee);

        return new EmployeeOutputDTO(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getRole(),
            employee.getDepartment(),
            employee.getStatus().getName()
        );
    }

    public FindManyEmployeesOutputDTO findMany(FindManyEmployeesInputDTO input) {
        List<Employee> employeesEntities = repository.findAll(
            input.name(),
            input.email(),
            input.role(),
            input.status(),
            input.skip(),
            input.take()
        );

        List<EmployeeOutputDTO> employees = employeesEntities.stream()
                .map(employee -> new EmployeeOutputDTO(
                        employee.getId(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getRole(),
                        employee.getDepartment(),
                        employee.getStatus().getName()
                ))
                .toList();

        return new FindManyEmployeesOutputDTO(
            employees,
            repository.count()
        );
    }
}
