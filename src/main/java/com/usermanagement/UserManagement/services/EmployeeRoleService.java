/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.controllers.RoleController;
import com.usermanagement.UserManagement.entities.EmployeeRole;
import com.usermanagement.UserManagement.repositories.EmployeeRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class EmployeeRoleService {
    
    @Autowired EmployeeRoleRepository repository;
    
    public Iterable<EmployeeRole> getAll(){
        return repository.findAll();
    }
    
    public boolean save(EmployeeRole employeeRole){
        if (repository.save(employeeRole)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void delete(Integer id)
    {
        repository.deleteById(id);
    }
    
    public List<EmployeeRole> getSession(String email){
        return repository.getRole(email);
    }
    
    public EmployeeRole getById(Integer id){
        return repository.findById(id).get();
    }
    
//    public EmployeeRole getSession(String email){
//        return repository.getSession(email);
//    }
}
