/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.entities.Marital;
import com.usermanagement.UserManagement.repositories.MaritalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Service
public class MaritalService {
    
    @Autowired
    MaritalRepository repository;
    
    public Iterable<Marital> getAll() {
        return repository.findAll();
    }
    
    public Marital getById(String id) {
        return repository.findById(id).get();
    }
    
    public Marital getByName(String name) {
        return repository.getByName(name);
    }
    
}
