package com.example.rhpicpaybackend.shared.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EmployeeStatusEnum {

    // TO DO: refactor the name of enum status to a message's param
    UNDER_REVIEW("Under Review", 1),
    APPROVED("Approved", 2),
    REJECTED("Rejected", 3),
    HIRED("Hired", 4);

    private String name;
    private int id;

    public static EmployeeStatusEnum fromName(String name) {
        for (EmployeeStatusEnum status : values()) {
            if(status.getName().equals(name)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + name);
    }

    public static EmployeeStatusEnum fromId(int id) {
        for (EmployeeStatusEnum status : values()) {
            if(status.getId() == id) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + id);
    }

}
