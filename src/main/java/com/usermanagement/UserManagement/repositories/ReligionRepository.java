/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.repositories;

import com.usermanagement.UserManagement.entities.Religion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public interface ReligionRepository extends CrudRepository<Religion, String> {

    @Query(value = "SELECT * FROM tb_m_religion where name = ?1", nativeQuery = true)
    public Religion getByName(String name);
}
