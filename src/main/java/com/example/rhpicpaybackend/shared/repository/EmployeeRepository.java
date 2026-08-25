package com.example.rhpicpaybackend.shared.repository;

import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.example.rhpicpaybackend.shared.exceptions.PicPayException;
import com.example.rhpicpaybackend.shared.model.Employee;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class EmployeeRepository {

    // Lista dos funcionários (simulação de armazenamento de dados)
    private final Map<Long, Employee> employees = new HashMap<>();
    private Long id = 0L;

    // Simulação de Dataload
    @PostConstruct
    private void initializeData() {
        save(new Employee(
                "Ryan Cursino",
                "ryan.cursino@picpay.com",
                "(11) 99999-0001",
                "Desenvolvedor Backend",
                "Tecnologia",
                5500.00,
                "São Paulo",
                EmployeeStatusEnum.APPROVED
        ));

        save(new Employee(
                "Lucas Almeida",
                "lucas.almeida@picpay.com",
                "(11) 99999-0002",
                "Desenvolvedor Frontend",
                "Tecnologia",
                5200.00,
                "São Paulo",
                EmployeeStatusEnum.UNDER_REVIEW
        ));

        save(new Employee(
                "Mariana Santos",
                "mariana.santos@picpay.com",
                "(21) 99999-0003",
                "Analista de Dados",
                "Dados",
                6800.00,
                "Rio de Janeiro",
                EmployeeStatusEnum.APPROVED
        ));

        save(new Employee(
                "Gabriel Oliveira",
                "gabriel.oliveira@picpay.com",
                "(11) 99999-0004",
                "Engenheiro de Dados",
                "Dados",
                7500.00,
                "São Paulo",
                EmployeeStatusEnum.APPROVED
        ));

        save(new Employee(
                "Beatriz Ferreira",
                "beatriz.ferreira@picpay.com",
                "(31) 99999-0005",
                "Product Manager",
                "Produto",
                8500.00,
                "Belo Horizonte",
                EmployeeStatusEnum.HIRED
        ));

        save(new Employee(
                "João Pedro",
                "joao.pedro@picpay.com",
                "(41) 99999-0006",
                "Analista de Segurança",
                "Segurança",
                7200.00,
                "Curitiba",
                EmployeeStatusEnum.UNDER_REVIEW
        ));

        save(new Employee(
                "Ana Carolina",
                "ana.carolina@picpay.com",
                "(11) 99999-0007",
                "UX Designer",
                "Design",
                6100.00,
                "São Paulo",
                EmployeeStatusEnum.REJECTED
        ));

        save(new Employee(
                "Matheus Rodrigues",
                "matheus.rodrigues@picpay.com",
                "(51) 99999-0008",
                "Analista de Recursos Humanos",
                "Recursos Humanos",
                4800.00,
                "Porto Alegre",
                EmployeeStatusEnum.APPROVED
        ));

        save(new Employee(
                "Larissa Martins",
                "larissa.martins@picpay.com",
                "(11) 99999-0009",
                "Tech Lead",
                "Tecnologia",
                10500.00,
                "São Paulo",
                EmployeeStatusEnum.UNDER_REVIEW
        ));

        save(new Employee(
                "Felipe Costa",
                "felipe.costa@picpay.com",
                "(19) 99999-0010",
                "Analista Financeiro",
                "Financeiro",
                5900.00,
                "Campinas",
                EmployeeStatusEnum.UNDER_REVIEW
        ));
    }

    // Métodos CRUD
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

    public Employee findById(Long id) {
        return employees.get(id);
    }

    public Employee fullUpdate(Employee originalEmployee, EmployeeRequestDTO dto) {
        originalEmployee.setName(dto.getName());
        originalEmployee.setEmail(dto.getEmail());
        originalEmployee.setPhone(dto.getPhone());
        originalEmployee.setRole(dto.getRole());
        originalEmployee.setDepartment(dto.getDepartment());
        originalEmployee.setSalary(dto.getSalary());
        originalEmployee.setCity(dto.getCity());

        if (dto.getStatus() != null) {
            originalEmployee.setStatus(EmployeeStatusEnum.fromId(dto.getStatus()));
        }


        employees.put(originalEmployee.getId(), originalEmployee);
        return originalEmployee;
    }

    public Employee partialUpdate(Employee originalEmployee, EmployeeRequestDTO dto) {

        if (dto.getPhone() != null) {
            originalEmployee.setPhone(dto.getPhone());
        }

        if (dto.getRole() != null) {
            originalEmployee.setRole(dto.getRole());
        }

        if (dto.getDepartment() != null) {
            originalEmployee.setDepartment(dto.getDepartment());
        }

        if (dto.getSalary() != null) {
            originalEmployee.setSalary(dto.getSalary());
        }

        if (dto.getCity() != null) {
            originalEmployee.setCity(dto.getCity());
        }

        if (dto.getCity() != null) {
            originalEmployee.setCity(dto.getCity());
        }

        if (dto.getStatus() != null)
            originalEmployee.setStatus(EmployeeStatusEnum.fromId(dto.getStatus()));

        employees.put(originalEmployee.getId(), originalEmployee);
        return originalEmployee;
    }

    public void deleteById(Long id) {
        employees.remove(id);
    }

    public Integer count(){
        return employees.size();
    }


}
