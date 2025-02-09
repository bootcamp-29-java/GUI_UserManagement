/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.Role;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    @Query(value = "SELECT * FROM tb_m_employee where email = ?1", nativeQuery = true)
    public Employee getByEmail(String email);

    @Query(value = "SELECT * FROM tb_m_employee where email = ?1", nativeQuery = true)
    public boolean getEmail(String email);

}
