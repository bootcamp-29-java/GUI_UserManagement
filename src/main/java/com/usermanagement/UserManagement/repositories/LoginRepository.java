/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface LoginRepository extends CrudRepository<Account, String>{
    @Query(value="From Account where employee.email=:email")
   public Account getByEmail(@Param("email")String email);
   
  
   
}
