package com.example.rhpicpaybackend.shared.enums;

import com.example.rhpicpaybackend.shared.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EmployeeStatusEnum {
    UNDER_REVIEW("employee.status.under-review", 1),
    APPROVED("employee.status.approved", 2),
    REJECTED("employee.status.rejected", 3),
    HIRED("employee.status.hired", 4);

    private String message;
    private int id;

    public static EmployeeStatusEnum fromId(Integer id) {
        if (id != null) {
            for (EmployeeStatusEnum status : values()) {
                if (status.getId() == id) {
                    return status;
                }
            }

            throw new NotFoundException("exception.employee-status.not-found");
        }

        return null;
    }

}
