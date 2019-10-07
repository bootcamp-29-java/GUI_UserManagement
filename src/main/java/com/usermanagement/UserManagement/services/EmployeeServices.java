/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class EmployeeServices {
    @Autowired
     EmployeeRepository repository;
    
    public Iterable<Employee> getAll(){
        return repository.findAll();
    }
    
    public Employee getByEmail(String email){
       return repository.getByEmail(email);
    }
    
//    public Employee getByEmail(String email){
//        Employee emp = new Employee();
//        
//        emp = repository.getByEmail(email);
//        return emp;
//    }
    
    public boolean save(Employee employee){
        if (repository.save(employee)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void delete(String id){
        repository.deleteById(id);
    }
    
    public Employee getById(String id){
          return repository.findById(id).get();
      }
}
