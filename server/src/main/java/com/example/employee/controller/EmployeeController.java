package com.example.employee.controller;

import com.example.employee.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.employee.exceptions.ResourceNotFoundException;
import com.example.employee.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins= "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();
    }

    //create an employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee)
    {
        return employeeRepository.save(employee);
    }

    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no employee found with this id:"+id));
        return ResponseEntity.ok(employee);
    }

    //update employee by id

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee newEmployee)
    {

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no employee found with this id:"+id));
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setEmailId(newEmployee.getEmailId());
       

        Employee updatedEmployeeinfo = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployeeinfo);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity< Map<String,Boolean>> deleteEmployee(@PathVariable Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("no employee is found with this id "+id));
        employeeRepository.delete(employee);
        Map<String,Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
