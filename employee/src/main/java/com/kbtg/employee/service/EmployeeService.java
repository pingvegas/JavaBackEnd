package com.kbtg.employee.service;

import com.kbtg.employee.model.Employee;
import com.kbtg.employee.model.rest.UpdateEmployeeDetail;
import com.kbtg.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.findById(id).get();
        }
        return null;
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findEmployeeByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrNicknameContainsIgnoreCase(name, name, name);
    }

    public Employee createEmployee(Employee employee) {
        employee.setStatus("current");
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        Employee employeeDB = getEmployee(employee.getId());
        if (employeeDB != null) {
            employeeDB.setFirstName(employee.getFirstName());
            employeeDB.setLastName(employee.getLastName());
            employeeDB.setNickname(employee.getNickname());
            employeeDB.setAddress(employee.getAddress());
            return employeeRepository.save(employeeDB);
        }
        return null;
    }

    public Employee updateEmployeeSalary(int id, UpdateEmployeeDetail updateEmployeeDetail) {
        double upSalaryPercent = updateEmployeeDetail.getUpSalaryPercent() / 100.0;
        if (0.0 <= upSalaryPercent && upSalaryPercent <= 100.0) {
            Employee employeeDB = getEmployee(id);
            if (employeeDB != null) {
                employeeDB.setSalary(employeeDB.getSalary() + (employeeDB.getSalary() * upSalaryPercent));
                return employeeRepository.save(employeeDB);
            }
        }
        return null;
    }

    public Employee updateEmployeePosition(int id, UpdateEmployeeDetail updateEmployeeDetail) {
        String oldPosition = updateEmployeeDetail.getOldPosition();
        String newPosition = updateEmployeeDetail.getNewPosition();
        Employee employeeDB = getEmployee(id);
        if (employeeDB != null) {
            if (oldPosition.equals(employeeDB.getPosition())) {
                employeeDB.setPosition(newPosition);
                return employeeRepository.save(employeeDB);
            }
        }
        return null;
    }

    public Employee deleteEmployee(int id) {
        Employee employeeDB = getEmployee(id);
        if (employeeDB != null) {
            employeeDB.setStatus("deleted");
            return employeeRepository.save(employeeDB);
        }
        return null;
    }

    public List<Integer> deleteEmployees(int[] ids) {
        List<Integer> notFound = new ArrayList<>();
        for (int id : ids) {
            Employee employeeDB = deleteEmployee(id);
            if (employeeDB == null) {
                notFound.add(id);
            }
        }
        return notFound;
    }
}
