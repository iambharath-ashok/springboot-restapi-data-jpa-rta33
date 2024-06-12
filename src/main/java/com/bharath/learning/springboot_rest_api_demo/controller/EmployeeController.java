package com.bharath.learning.springboot_rest_api_demo.controller;


import com.bharath.learning.springboot_rest_api_demo.model.EmployeeEntity;
import com.bharath.learning.springboot_rest_api_demo.service.EmployeeServiceV1;
import com.bharath.learning.springboot_rest_api_demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/api/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceV2")
    private IEmployeeService employeeService;


    //GET,POST,PUT,DELETE


    //GET all Employees
    //v1/api/employees
    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    //GET EmployeeEntity By Id
    //v1/api/employee/{id}
    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable("id") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    //GET EmployeeEntity By Salary
    //v1/api/employee/{salary}
    @GetMapping("/salary/{salary}")
    public List<EmployeeEntity> getEmployeeById(@PathVariable("salary") double employeeSalary) {
        return employeeService.getEmployeeBySalary(employeeSalary);
    }

    //CREATE New EmployeeEntity
    //v1/api/employeeEntity
    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        return employeeService.createEmployee(employeeEntity);
    }


    //DELETE EmployeeEntity
    //v1/api/employee/{id}
    @DeleteMapping("{id}")
    public boolean deleteEmployeeById(@PathVariable("id") int employeeId) {
        return employeeService.removeEmployeeById(employeeId);
    }


    //Update EmployeeEntity
    //v1/api/employeeEntity/{id}
    @PutMapping("/{id}")
    public EmployeeEntity updateEmployeeById(@PathVariable("id") int employeeId,
                                             @RequestBody EmployeeEntity employeeEntity) {
        EmployeeEntity updatedEmployeeEntity = employeeService.updateEmployeeById(employeeId, employeeEntity);
        return updatedEmployeeEntity;
    }

}
