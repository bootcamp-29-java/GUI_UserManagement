/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.configuration;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.EmployeeRole;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import com.usermanagement.UserManagement.repositories.EmployeeRoleRepository;
import com.usermanagement.UserManagement.services.EmployeeServices;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import static javafx.scene.input.KeyCode.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Reza
 */
@Service("userDetailsService")
@Transactional
public class MyUserDetailService implements UserDetailsService{
    private static final Logger log = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired EmployeeServices empService;
    @Autowired EmployeeRepository empRepo;
    @Autowired EmployeeRoleRepository emproleRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee emp = empRepo.getByEmail(email);
        
        if (email==null) {
            throw new UsernameNotFoundException("Email "+email+" TIDAK DITEMUKAN");
        }
        System.out.println("coba employee "+emp.getEmail());
        System.out.println("coba employee "+emp.getAccount().getPassword());
        System.out.println("coba Authority "+getAuthority(emp));
        
        
        
        UserDetails userDetails= (UserDetails) new User(emp.getEmail(), emp.getAccount().getPassword(), getAuthority(emp));
        System.out.println("Coba aja sih "+userDetails);
        return userDetails;
        
    }
    
    private static Collection<? extends GrantedAuthority> getAuthority(Employee employee){
        String[] EmployeeRole= employee.getEmployeeRoleList().stream().map((role)->role.getRole().getName()).toArray(String[]::new);
        System.out.println(EmployeeRole);
        Collection<GrantedAuthority> authority = AuthorityUtils.createAuthorityList(EmployeeRole);
        return authority;
    }


}
