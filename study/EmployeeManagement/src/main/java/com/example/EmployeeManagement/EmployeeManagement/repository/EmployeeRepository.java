package com.example.EmployeeManagement.EmployeeManagement.repository;


import com.example.EmployeeManagement.EmployeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}