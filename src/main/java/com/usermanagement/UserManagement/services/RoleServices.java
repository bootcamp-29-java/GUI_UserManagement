/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.entities.Role;
import com.usermanagement.UserManagement.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class RoleServices {
    @Autowired RoleRepository repository;
    
    public Iterable<Role>getAll(){
        return repository.findAll();
    }
    
    public boolean save(Role role){
        if (repository.save(role)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void delete(String id){
        repository.deleteById(id);
    }
    
    public Role getById(String id){
        return repository.findById(id).get();
    }
    
   

}
