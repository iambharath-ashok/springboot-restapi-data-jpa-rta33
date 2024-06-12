package com.bharath.learning.springboot_rest_api_demo.service;

import com.bharath.learning.springboot_rest_api_demo.model.EmployeeEntity;
import com.bharath.learning.springboot_rest_api_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceV2 implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;




    public List<EmployeeEntity> getAllEmployees() {
          return employeeRepository.findAll();
    }



    public EmployeeEntity getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("EmployeeEntity By Id:: "+employeeId+ " Not Found"));
    }


    public List<EmployeeEntity> getEmployeeBySalary(double employeeSalary) {
        return employeeRepository.findBySalary(employeeSalary);
    }

    public EmployeeEntity createEmployee(EmployeeEntity inEmployeeEntity) {
       EmployeeEntity savedEmployee = employeeRepository.save(inEmployeeEntity);
       return savedEmployee;
    }

    public boolean removeEmployeeById(long employeeId) {
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeEntity updateEmployeeById(long employeeId, EmployeeEntity employeeEntity) {
        //Fetch employee by id
        EmployeeEntity employeeToBeUpdated = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee By Id:: "+ employeeId+ " Not found."));

        //update with the new employee values
        employeeToBeUpdated.setDepartment(employeeEntity.getDepartment());
        employeeToBeUpdated.setName(employeeEntity.getName());
        employeeToBeUpdated.setSalary(employeeEntity.getSalary());
        //update/save the new updated employee entity to DB
        EmployeeEntity employeeUpdated = employeeRepository.save(employeeToBeUpdated);
        return employeeUpdated;
    }
}
