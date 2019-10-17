/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import com.usermanagement.UserManagement.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reza
 */
@Service
public class LoginService {
    
    @Autowired
    LoginRepository repository;
    
    public boolean login(String email, String password){
        boolean result = false;
        Account acc = repository.getByEmail(email);
        
        if (acc.getStatus().equals(0)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
