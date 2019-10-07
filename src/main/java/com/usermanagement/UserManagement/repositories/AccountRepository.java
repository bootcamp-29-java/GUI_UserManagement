/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
   @Query(value="select * from tb_m_account",nativeQuery = true)
   public Account getByEmail();

   @Query(value="select * from tb_m_account where token = ?1",nativeQuery = true)
   public Account getByToken(String token);
  
   @Query(value="update account set password=?1, status='0' where token=?2", nativeQuery = true)
   public Account updateByToken(String password, String token);
}