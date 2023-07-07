package com.kbtg.employee.repository;

import com.kbtg.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeeByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrNicknameContainsIgnoreCase(String name1, String name2, String name3);
}
