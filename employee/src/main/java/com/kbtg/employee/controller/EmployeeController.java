package com.kbtg.employee.controller;

import com.kbtg.employee.model.Employee;
import com.kbtg.employee.model.rest.UpdateEmployeeDetail;
import com.kbtg.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Object getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>("Not found employee ID: "+id, HttpStatus.NOT_FOUND);
        }
        return employee;
    }

    @GetMapping("/name")
    public List<Employee> getEmployeesByName(@RequestParam String name) {
        return employeeService.getEmployeesByName(name);
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PutMapping("/salary/{id}")
    public Employee updateEmployeeSalary(@PathVariable int id,
                                         @RequestBody UpdateEmployeeDetail updateEmployeeDetail) {
        return employeeService.updateEmployeeSalary(id, updateEmployeeDetail);
    }

    @PutMapping("/position/{id}")
    public Employee updateEmployeePosition(@PathVariable int id,
                                           @RequestBody UpdateEmployeeDetail updateEmployeeDetail) {
        return employeeService.updateEmployeePosition(id, updateEmployeeDetail);
    }

    @DeleteMapping("/{id}")
    public Object deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.deleteEmployee(id);
        if (employee == null) {
            return new ResponseEntity<>("Not found employee ID: "+id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public Object deleteEmployees(@RequestParam int[] ids) {
        List<Integer> notFound =  employeeService.deleteEmployees(ids);
        if (notFound.size() > 0) {
            return new ResponseEntity<>("{not_found_ids: "+ Arrays.toString(notFound.toArray())+"}", HttpStatus.MULTI_STATUS);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
