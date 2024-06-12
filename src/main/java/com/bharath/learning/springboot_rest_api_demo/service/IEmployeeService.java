package com.bharath.learning.springboot_rest_api_demo.service;

import com.bharath.learning.springboot_rest_api_demo.model.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeEntity> getAllEmployees();
    EmployeeEntity getEmployeeById(long employeeId);

    List<EmployeeEntity> getEmployeeBySalary(double employeeSalary);

    EmployeeEntity createEmployee(EmployeeEntity inEmployeeEntity);

    boolean removeEmployeeById(long employeeId);

    EmployeeEntity updateEmployeeById(long employeeId, EmployeeEntity employeeEntity);
}
