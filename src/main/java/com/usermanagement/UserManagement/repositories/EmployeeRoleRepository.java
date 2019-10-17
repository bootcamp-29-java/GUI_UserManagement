/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.EmployeeRole;
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
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, Integer>{
    @Query(value = "SELECT r.* FROM TB_TR_Employee_Role r RIGHT JOIN TB_M_Employee e ON e.id = r.employee WHERE e.email = ?1", nativeQuery = true)
    public List<EmployeeRole> getRole(String email);
    
    @Query(value="select max(id+0)+1 from tb_tr_employee_role",nativeQuery = true)
    public String genIdEmployeeRole();
}
