package com.example.rhpicpaybackend.employee.service;

import com.example.rhpicpaybackend.employee.dto.input.FindManyEmployeesInputDTO;
import com.example.rhpicpaybackend.employee.dto.output.CountEmployeeOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeCardOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeDetailsOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.FindManyEmployeesOutputDTO;
import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.shared.exceptions.ConflictException;
import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.example.rhpicpaybackend.shared.helpers.NormalizeInput;
import com.example.rhpicpaybackend.shared.helpers.NormalizeOutput;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository repository;
    private final MessageService messageService;

    public EmployeeDetailsOutputDTO register(EmployeeRequestDTO input) {
        if (repository.findByEmail(input.getEmail()).isPresent()) throw new ConflictException("exception.employee-email.conflict");

        Employee employee = new Employee(
                NormalizeInput.name(input.getName()),
                NormalizeInput.email(input.getEmail()),
                passwordEncoder.encode(NormalizeInput.password(input.getPassword())),
                input.getPhone(),
                NormalizeInput.name(input.getRole()),
                NormalizeInput.name(input.getDepartment()),
                input.getSalary(),
                NormalizeInput.name(input.getCity()),
                EmployeeStatusEnum.UNDER_REVIEW
        );

        repository.save(employee);

        return new EmployeeDetailsOutputDTO(
            employee.getId(),
            NormalizeOutput.name(employee.getName()),
            NormalizeOutput.email(employee.getEmail()),
            NormalizeOutput.phone(employee.getPhone()),
            NormalizeOutput.name(employee.getRole()),
            NormalizeOutput.name(employee.getDepartment()),
            employee.getSalary(),
            NormalizeOutput.name(employee.getCity()),
            employee.getStatus()
        );
    }

    public FindManyEmployeesOutputDTO findMany(FindManyEmployeesInputDTO input) {
        List<Employee> employeesEntities = repository.findAll(
            input.name(),
            input.email(),
            input.role(),
            input.status(),
            input.skip(),
            input.take(),
            input.sortDirection()
        );

        List<EmployeeCardOutputDTO> employees = employeesEntities.stream()
                .map(employee -> new EmployeeCardOutputDTO(
                        employee.getId(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getRole(),
                        employee.getDepartment(),
                        employee.getStatus(),
                        employee.getCreatedAt()
                ))
                .toList();

        return new FindManyEmployeesOutputDTO(
            employees,
            repository.filteredCount(
                input.name(),
                input.email(),
                input.role(),
                input.status()
            )
        );
    }

    public EmployeeDetailsOutputDTO findOne(Long id) {
        Employee employee = find(id);

        return new EmployeeDetailsOutputDTO(
                employee.getId(),
                NormalizeOutput.name(employee.getName()),
                NormalizeOutput.email(employee.getEmail()),
                NormalizeOutput.phone(employee.getPhone()),
                NormalizeOutput.name(employee.getRole()),
                NormalizeOutput.name(employee.getDepartment()),
                employee.getSalary(),
                NormalizeOutput.name(employee.getCity()),
                employee.getStatus()
        );
    }

    public Map<EmployeeStatusEnum, Integer> countEmployeesStatus() {
        return repository.countEmployeesStatus();
    }

    public EmployeeDetailsOutputDTO fullUpdate(Long id, EmployeeRequestDTO input) {
        Employee originalEmployee = find(id);
        Employee employee = repository.fullUpdate(originalEmployee, input);

        return new EmployeeDetailsOutputDTO(
                employee.getId(),
                NormalizeOutput.name(employee.getName()),
                NormalizeOutput.email(employee.getEmail()),
                NormalizeOutput.phone(employee.getPhone()),
                NormalizeOutput.name(employee.getRole()),
                NormalizeOutput.name(employee.getDepartment()),
                employee.getSalary(),
                NormalizeOutput.name(employee.getCity()),
                employee.getStatus()
        );
    }

    public EmployeeDetailsOutputDTO partialUpdate(Long id, EmployeeRequestDTO input) {
        Employee originalEmployee = find(id);
        Employee updatedEmployee = repository.partialUpdate(originalEmployee, input);

        return new EmployeeDetailsOutputDTO(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getEmail(),
                updatedEmployee.getPhone(),
                updatedEmployee.getRole(),
                updatedEmployee.getDepartment(),
                updatedEmployee.getSalary(),
                updatedEmployee.getCity(),
                updatedEmployee.getStatus()
        );
    }

    public void delete(Long id) {
        Employee employee = find(id);

        repository.deleteById(id);
    }

    public CountEmployeeOutputDTO count() {
        return new CountEmployeeOutputDTO(
            repository.count()
        );
    }

    private Employee find(Long id){
        return repository.findById(id).orElseThrow(
            () -> new NotFoundException("exception.employee.not-found")
        );
    }
}
