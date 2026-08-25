package com.example.rhpicpaybackend.shared.repositories;

import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import com.example.rhpicpaybackend.shared.models.Employee;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class EmployeeRepository {

    private final Map<Long, Employee> employees = new HashMap<>();
    private Long id = 0L;

    public void save(Employee employee) {
        Long employeeId = ++id;

        employee.setId(employeeId);
        employees.put(employeeId, employee);
    }

    public List<Employee> findAll(String name, String email, String role, EmployeeStatusEnum status,
                                  Integer skip, Integer take) {
        Stream<Employee> stream = employees.values().stream();

        if (name != null && !name.isBlank())
            stream = stream.filter(employee -> employee.getName().contains(name));

        if (email != null && !email.isBlank())
            stream = stream.filter(employee -> employee.getEmail().equals(email));

        if (role != null && !role.isBlank())
            stream = stream.filter(employee -> employee.getRole().equals(role));

        if (status != null)
            stream = stream.filter(employee -> employee.getStatus().equals(status));

        List<Employee> result = stream.toList();

        int safeSkip = skip != null && skip >= 0 ? skip : 0;
        int safeTake = take != null && take >= 0 ? take : 5;

        int fromIndex = Math.min(safeSkip, result.size());
        int toIndex = Math.min(safeSkip + safeTake, result.size());

        return result.subList(fromIndex, toIndex);
    }

    public Optional<Employee> findByEmail(String email){
        return employees.values()
            .stream()
            .filter(employee -> employee.getEmail().equals(email))
            .findFirst();
    }

    public Optional<Employee> findByEmailAndStatus(String email, EmployeeStatusEnum status){
        return employees.values()
            .stream()
            .filter(employee -> employee.getEmail().equals(email) && employee.getStatus().equals(status))
            .findFirst();
    }

    public Optional<Employee> findByEmailAndContainingRole(String email, String role){
        return employees.values()
            .stream()
            .filter(employee -> employee.getEmail().equals(email) && employee.getRole().contains(role))
            .findFirst();
    }

    public Integer count(){
        return employees.size();
    }


}
