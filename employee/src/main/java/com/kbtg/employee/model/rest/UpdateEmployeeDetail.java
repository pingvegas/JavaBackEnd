package com.kbtg.employee.model.rest;

import lombok.Data;

@Data
public class UpdateEmployeeDetail {
    private double upSalaryPercent;
    private String oldPosition;
    private String newPosition;
}
