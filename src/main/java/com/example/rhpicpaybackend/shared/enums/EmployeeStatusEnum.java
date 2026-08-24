package com.example.rhpicpaybackend.shared.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EmployeeStatusEnum {
    UNDER_REVIEW("Under Review", 1),
    APPROVED("Approved", 2),
    REJECTED("Rejected", 3),
    HIRED("Hired", 4);

    private String name;
    private int id;

    public static EmployeeStatusEnum fromName(String name) {
        for (EmployeeStatusEnum statusProblema : values()) {
            if(statusProblema.getName().equals(name)) {
                return statusProblema;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + name);
    }

    public static EmployeeStatusEnum fromId(int id) {
        for (EmployeeStatusEnum statusProblema : values()) {
            if(statusProblema.getId() == id) {
                return statusProblema;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + id);
    }

}
