/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, String>{
    @Query(value="select max(id+0)+1 from tb_m_role",nativeQuery = true)
    public String genIdRole();
}
