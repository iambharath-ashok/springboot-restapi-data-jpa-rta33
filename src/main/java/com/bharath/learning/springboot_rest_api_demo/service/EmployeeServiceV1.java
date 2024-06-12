package com.bharath.learning.springboot_rest_api_demo.service;

import com.bharath.learning.springboot_rest_api_demo.model.EmployeeEntity;
import com.bharath.learning.springboot_rest_api_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class EmployeeServiceV1 implements IEmployeeService {

    private List<EmployeeEntity> employeesList = new ArrayList<>();
    public EmployeeServiceV1() {
        employeesList.add(new EmployeeEntity(1l,"Emp1", 1000, "IT"));
        employeesList.add(new EmployeeEntity(2l,"Emp2", 3000, "HR"));
        employeesList.add(new EmployeeEntity(6l,"Emp6", 3000, "IT"));
        employeesList.add(new EmployeeEntity(3l,"Emp3", 5000, "FINANCE"));
        employeesList.add(new EmployeeEntity(4l,"Emp4", 2000, "IT"));
        employeesList.add(new EmployeeEntity(5l,"Emp5", 10000, "SERVICE"));
    }


    public List<EmployeeEntity> getAllEmployees() {
          return employeesList;
    }



    public EmployeeEntity getEmployeeById(long employeeId) {
        return employeesList.stream()
                .filter(employeeEntity -> employeeEntity.getId() == employeeId)
                .findFirst()
                .get();
    }


    public List<EmployeeEntity> getEmployeeBySalary(double employeeSalary) {
        return employeesList.stream()
                .filter(employeeEntity -> employeeEntity.getSalary() == employeeSalary)
                .collect(Collectors.toList());
    }

    public EmployeeEntity createEmployee(EmployeeEntity inEmployeeEntity) {
        if(employeesList!=null && !employeesList.isEmpty()) {
            long lastEmployeeId = employeesList
                    .stream()
                    .map(employeeEntity -> employeeEntity.getId())
                    .sorted(Comparator.reverseOrder())
                    .findFirst()
                    .get();
            inEmployeeEntity.setId(++lastEmployeeId);
            employeesList.add(inEmployeeEntity);
            EmployeeEntity outEmployeeEntity = getEmployeeById(inEmployeeEntity.getId());
            return outEmployeeEntity;
        } else {
            inEmployeeEntity.setId(1l);
            employeesList.add(inEmployeeEntity);
            return inEmployeeEntity;
        }
    }

    public boolean removeEmployeeById(long employeeId) {
        EmployeeEntity employeeEntityToBeRemoved = getEmployeeById(employeeId);
        boolean isRemoved = employeesList.remove(employeeEntityToBeRemoved);
        return isRemoved;
    }

    public EmployeeEntity updateEmployeeById(long employeeId, EmployeeEntity employeeEntity) {
        if (employeesList != null) {
          EmployeeEntity employeeEntityToBeUpdated =  employeesList.stream()
                  .filter(e -> e.getId() == employeeId).findFirst()
                    .orElseThrow(() ->
                         new RuntimeException("EmployeeEntity By Id:: "+employeeId+ " Not Found"));
          employeeEntityToBeUpdated.setId(employeeId);
          employeeEntityToBeUpdated.setName(employeeEntity.getName());
          employeeEntityToBeUpdated.setDepartment(employeeEntity.getDepartment());
          employeeEntityToBeUpdated.setSalary(employeeEntity.getSalary());
          return employeeEntityToBeUpdated;
        } else {
            throw new RuntimeException("EmployeeEntity Not Found");
        }
    }
}
