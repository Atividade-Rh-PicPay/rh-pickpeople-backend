package com.example.rhpicpaybackend.employee.service;

import com.example.rhpicpaybackend.employee.dto.input.FindManyEmployeesInputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeCardOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.EmployeeDetailsOutputDTO;
import com.example.rhpicpaybackend.employee.dto.output.FindManyEmployeesOutputDTO;
import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.shared.model.Employee;
import com.example.rhpicpaybackend.shared.repository.EmployeeRepository;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeCardOutputDTO register(EmployeeRequestDTO input) {
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

        return new EmployeeCardOutputDTO(
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

        List<EmployeeCardOutputDTO> employees = employeesEntities.stream()
                .map(employee -> new EmployeeCardOutputDTO(
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

    public EmployeeDetailsOutputDTO findOne(Long id) {
        Employee employee = repository.findById(id);

        return new EmployeeDetailsOutputDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getRole(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getCity(),
                employee.getStatus().getName()
        );
    }

    public EmployeeDetailsOutputDTO fullUpdate(Long id, EmployeeRequestDTO input) {
        Employee originalEmployee = repository.findById(id);
        Employee updatedEmployee = repository.fullUpdate(originalEmployee, input);

        return new EmployeeDetailsOutputDTO(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getEmail(),
                updatedEmployee.getPhone(),
                updatedEmployee.getRole(),
                updatedEmployee.getDepartment(),
                updatedEmployee.getSalary(),
                updatedEmployee.getCity(),
                updatedEmployee.getStatus().getName()
        );
    }

    public EmployeeDetailsOutputDTO partialUpdate(Long id, EmployeeRequestDTO input) {
        Employee originalEmployee = repository.findById(id);
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
                updatedEmployee.getStatus().getName()
        );
    }

    public String deleteOne(Long id) {
        Employee employee = repository.findById(id);

        repository.deleteById(id);
        return employee.getName();
    }
}
