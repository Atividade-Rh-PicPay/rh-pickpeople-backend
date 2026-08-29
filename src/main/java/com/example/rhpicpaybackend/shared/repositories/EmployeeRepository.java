package com.example.rhpicpaybackend.shared.repositories;

import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.shared.exceptions.ConflictException;
import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.example.rhpicpaybackend.shared.helpers.NormalizeInput;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.enums.EmployeeStatusEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class EmployeeRepository {

    // Lista dos funcionários (simulação de armazenamento de dados)
    private final Map<Long, Employee> employees = new HashMap<>();
    private Long id = 0L;
    private final PasswordEncoder passwordEncoder;

    public EmployeeRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Simulação de Dataload
    @PostConstruct
    private void initializeData() {
        save(new Employee(
                "Ryan Cursino",
                "ryan.cursino@picpay.com",
                passwordEncoder.encode("Senha123"),
                "(11) 99999-0001",
                "Analista de Recursos Humanos",
                "Recursos Humanos",
                5500.00,
                "São Paulo",
                EmployeeStatusEnum.APPROVED
        ));

        save(new Employee(
                "Lucas Almeida",
                "lucas.almeida@picpay.com",
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
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
                passwordEncoder.encode("Senha123"),
                "(19) 99999-0010",
                "Analista Financeiro",
                "Financeiro",
                5900.00,
                "Campinas",
                EmployeeStatusEnum.UNDER_REVIEW
        ));

        save(new Employee(
            "caio marcos",
            "caio.marcos@picpay.com",
            passwordEncoder.encode("Senha123"),
            "(19) 99999-0010",
            "analista de recursos humanos",
            "recursos humanos",
            5900.00,
            "campinas",
            EmployeeStatusEnum.APPROVED
        ));
    }

    // Métodos CRUD
    public void save(Employee employee) {
        Long employeeId = ++id;

        employee.setId(employeeId);
        employees.put(employeeId, employee);
    }

    public List<Employee> findAll(String name, String email, String role, EmployeeStatusEnum status,
                                  Integer skip, Integer take, Integer sortDirection) {
        Stream<Employee> stream = employees.values().stream();

        if (name != null && !name.isBlank())
            stream = stream.filter(employee -> employee.getName().contains(name));

        if (email != null && !email.isBlank())
            stream = stream.filter(employee -> employee.getEmail().equals(email));

        if (role != null && !role.isBlank())
            stream = stream.filter(employee -> employee.getRole().equals(role));

        if (status != null)
            stream = stream.filter(employee -> employee.getStatus().equals(status));

        stream = stream.sorted(
                sortDirection == null || sortDirection == 0
                        ? Comparator.comparing(Employee::getCreatedAt)
                        : Comparator.comparing(Employee::getCreatedAt).reversed()
        );


        List<Employee> result = stream.toList();

        int safeSkip = skip != null && skip >= 0 ? skip : 0;
        int safeTake = take != null && take >= 0 ? take : 5;

        int fromIndex = Math.min(safeSkip, result.size());
        int toIndex = Math.min(safeSkip + safeTake, result.size());

        return result.subList(fromIndex, toIndex);
    }

    public Integer filteredCount(String name, String email, String role, EmployeeStatusEnum status){
        Stream<Employee> stream = employees.values().stream();

        if (name != null && !name.isBlank())
            stream = stream.filter(employee -> employee.getName().contains(name));

        if (email != null && !email.isBlank())
            stream = stream.filter(employee -> employee.getEmail().equals(email));

        if (role != null && !role.isBlank())
            stream = stream.filter(employee -> employee.getRole().equals(role));

        if (status != null)
            stream = stream.filter(employee -> employee.getStatus().equals(status));

        return stream.toList().size();
    }

    public Optional<Employee> findByEmail(String email){
        return employees.values()
            .stream()
            .filter(employee -> employee.getEmail().equals(email))
            .findFirst();
    }

    public Optional<Employee> findById(Long id) {
        Employee employee = employees.get(id);

        return Optional.of(employee);
    }

    public Map<EmployeeStatusEnum, Integer> countEmployeesStatus() {
        Map<EmployeeStatusEnum, Integer> map = new HashMap<>();

        for (int i = 1; i <= EmployeeStatusEnum.values().length; i++) {

            Stream<Employee> stream = employees.values().stream();

            EmployeeStatusEnum status = EmployeeStatusEnum.fromId(i);
            stream = stream.filter(employee -> employee.getStatus() == status);
            map.put(status, stream.toList().size());
        }

        return map;
    }

    public Employee fullUpdate(Employee employee, EmployeeRequestDTO input) {
        if (findByEmail(input.getEmail()).isPresent()) throw  new NotFoundException("{exception.employee-email.conflict}");

        employee.setName(input.getName());
        employee.setEmail(input.getEmail());
        employee.setPassword(passwordEncoder.encode(input.getPassword()));
        employee.setPhone(input.getPhone());
        employee.setRole(input.getRole());
        employee.setDepartment(input.getDepartment());
        employee.setSalary(input.getSalary());
        employee.setCity(input.getCity());

        if (input.getStatus() != null) employee.setStatus(EmployeeStatusEnum.fromId(input.getStatus()));

        employees.put(employee.getId(), employee);

        return employee;
    }

    public Employee partialUpdate(Employee employee, EmployeeRequestDTO input) {
        if (input.getName() != null && employee.getName() != NormalizeInput.name(input.getName()))
            employee.setName(input.getName());

        if (input.getEmail() != null && employee.getEmail() != NormalizeInput.email(input.getEmail())) {
            if (findByEmail(input.getEmail()).isPresent()) throw new ConflictException("exception.employee-email.conflict");

            employee.setEmail(input.getEmail());
        }

        if (input.getPassword() != null) employee.setPassword(passwordEncoder.encode(input.getPassword()));

        if (input.getPhone() != null && employee.getPhone() != NormalizeInput.phone(input.getPhone())) employee.setPhone(input.getPhone());

        if (input.getRole() != null && employee.getRole() != NormalizeInput.name(input.getRole())) employee.setRole(input.getRole());

        if (input.getDepartment() != null && employee.getDepartment() != NormalizeInput.name(input.getDepartment())) employee.setDepartment(input.getDepartment());

        if (input.getSalary() != null) employee.setSalary(input.getSalary());

        if (input.getCity() != null && employee.getCity() != NormalizeInput.name(input.getCity())) employee.setCity(input.getCity());

        if (input.getStatus() != null && employee.getStatus() != EmployeeStatusEnum.fromId(input.getStatus())) employee.setStatus(EmployeeStatusEnum.fromId(input.getStatus()));

        employees.put(employee.getId(), employee);

        return employee;
    }

    public void deleteById(Long id) {
        employees.remove(id);
    }

    public Integer count(){
        return employees.size();
    }
}
