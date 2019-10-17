/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.configuration;

import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Reza
 */
public class UserPrincipal implements UserDetails{

    
    private Employee employee;
    private List<String> roles;

    public UserPrincipal(Employee employee, List<String> roles) {
        super();
        this.employee = employee;
        this.roles = roles;

    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            list.add(new SimpleGrantedAuthority(role));
        }
        return list;
        
    }

    @Override
    public String getPassword() {
            
        return employee.getAccount().getPassword();
    }

    @Override
    public String getUsername() {
        
        return employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }

    @Override
    public boolean isEnabled() {
        
        return true;
    }
    
}
