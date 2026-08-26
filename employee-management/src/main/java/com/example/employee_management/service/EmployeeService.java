package com.example.employee_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_management.Repository.EmployeeRepository;
import com.example.employee_management.entity.Employee;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Add employee
    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Get all employees
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    // Get employee by ID
    public Employee getEmployee(int id) {
        return repository.findById(id).orElse(null);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee employee) {

        Employee oldEmployee = repository.findById(id).orElse(null);

        if (oldEmployee != null) {
            oldEmployee.setName(employee.getName());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setSalary(employee.getSalary());

            return repository.save(oldEmployee);
        }

        return null;
    }

    // Delete employee
    public String deleteEmployee(int id) {
        repository.deleteById(id);
        return "Employee deleted successfully";
    }
}