package com.employeemanagementservice.api;

import java.util.ArrayList;
import java.util.List;

import com.employeemanagementservice.entity.Employee;
import com.employeemanagementservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@RequestBody Employee employeeRequest, @PathVariable(required = true,value = "id") long id){
        Employee emp = employeeRepository.findById(id);
        if(emp != null){
            emp.setName(employeeRequest.getName());
            employeeRepository.save(emp);
            return  emp;
        } else {
            throw new IllegalArgumentException("Id not available in Employee List");
        }
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable(required = true,value = "id") long id){
        employeeRepository.deleteById(id);
    }
}
