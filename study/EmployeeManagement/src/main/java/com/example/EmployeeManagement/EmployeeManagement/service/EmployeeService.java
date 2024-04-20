package com.example.EmployeeManagement.EmployeeManagement.service;

import com.example.EmployeeManagement.EmployeeManagement.model.Employee;

import java.util.List;

public interface EmployeeService {
        Employee saveEmployee(Employee employee);
        List<Employee> getAllEmployees();
        Employee getEmployeeById(long id);
        Employee updateEmployee(Employee employee, long id);
        void deleteEmployee(long id);
}
