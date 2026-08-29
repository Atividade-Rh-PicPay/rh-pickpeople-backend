package com.example.rhpicpaybackend.profile.service;

import com.example.rhpicpaybackend.employee.dto.request.EmployeeRequestDTO;
import com.example.rhpicpaybackend.profile.dto.input.FindMyProfileInputDTO;
import com.example.rhpicpaybackend.profile.dto.input.UpdateMyUserInputDTO;
import com.example.rhpicpaybackend.profile.dto.output.FindMyProfileOutputDTO;
import com.example.rhpicpaybackend.profile.dto.request.UpdateMyUserRequestDTO;
import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.example.rhpicpaybackend.shared.helpers.NormalizeOutput;
import com.example.rhpicpaybackend.shared.models.Employee;
import com.example.rhpicpaybackend.shared.repositories.EmployeeRepository;
import com.example.rhpicpaybackend.shared.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
  private final EmployeeRepository employeeRepository;

  private final MessageService messageService;

  public FindMyProfileOutputDTO findMyProfile(FindMyProfileInputDTO input){
    Employee employee = find(input.id());

    return new FindMyProfileOutputDTO(
        employee.getId(),
        NormalizeOutput.name(employee.getName()),
        NormalizeOutput.email(employee.getEmail()),
        NormalizeOutput.phone(employee.getPhone()),
        NormalizeOutput.name(employee.getRole()),
        NormalizeOutput.name(employee.getDepartment()),
        employee.getSalary(),
        NormalizeOutput.name(employee.getCity()),
        messageService.getMessage(employee.getStatus().getMessage())
    );
  }

  public FindMyProfileOutputDTO updatePartial(UpdateMyUserInputDTO input){
    Employee employee = find(input.id());
    Employee updatedEmployee = employeeRepository.partialUpdate(employee, new EmployeeRequestDTO(
        input.name(),
        input.email(),
        input.password(),
        input.phone(),
        employee.getRole(),
        employee.getDepartment(),
        employee.getSalary(),
        input.city(),
        employee.getStatus().getId()
    ));

    return new FindMyProfileOutputDTO(
        updatedEmployee.getId(),
        NormalizeOutput.name(updatedEmployee.getName()),
        NormalizeOutput.email(updatedEmployee.getEmail()),
        NormalizeOutput.phone(updatedEmployee.getPhone()),
        NormalizeOutput.name(updatedEmployee.getRole()),
        NormalizeOutput.name(updatedEmployee.getDepartment()),
        updatedEmployee.getSalary(),
        NormalizeOutput.name(updatedEmployee.getCity()),
        messageService.getMessage(updatedEmployee.getStatus().getMessage())
    );
  }
  private Employee find(Long id){
    return employeeRepository.findById(id).orElseThrow(
        () -> new NotFoundException("exception.employee.not-found")
    );
  }
}
