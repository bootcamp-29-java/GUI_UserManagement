/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.EmployeeRole;
import com.usermanagement.UserManagement.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usermanagement.UserManagement.repositories.AccountRepository;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Reza
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository repository;
    EmployeeRepository repository2;
    
    public Iterable<Account> getAll(){
        return repository.findAll();
    }
    
    public Account getByToken(String token){
        return repository.getByToken(token);
    }
    
    public boolean save(Account account){
        if (repository.save(account)!=null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void delete(String id){
        repository.deleteById(id);
    }
    
    public Account getById(String id){
        return repository.findById(id).get();
    }
    
    public Account updateByToken(String password, String token){
        Account acc = repository.updateByToken(password, token);
        
        return acc;
    }
}
