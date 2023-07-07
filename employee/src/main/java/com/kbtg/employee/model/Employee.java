package com.kbtg.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String nickname;
    private double salary;
    private String status;
    private String address;
    private String position;
}
