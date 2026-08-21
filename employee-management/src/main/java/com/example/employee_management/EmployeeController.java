package com.example.employee_management;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        Employee oldEmployee =
                repository.findById(id).orElse(null);

        if (oldEmployee != null) {

            oldEmployee.setName(employee.getName());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setSalary(employee.getSalary());

            return repository.save(oldEmployee);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {

        repository.deleteById(id);

        return "Employee deleted successfully";
    }
}