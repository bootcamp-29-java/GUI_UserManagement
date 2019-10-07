/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.controllers;

import com.usermanagement.UserManagement.entities.EmployeeDummy;
import com.usermanagement.UserManagement.entities.EmployeeRole;
import com.usermanagement.UserManagement.services.AccountService;
import com.usermanagement.UserManagement.services.EmployeeRoleService;
import com.usermanagement.UserManagement.services.LoginRest;
import com.usermanagement.UserManagement.services.LoginService;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Reza
 */
@Controller
public class LoginController {
    @Autowired
    AccountService accService;
    @Autowired
    LoginService logService;
    @Autowired
    EmployeeRoleService emproleService;
    @Autowired
    LoginRest rest;
    
    public List<EmployeeRole> getSession(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return emproleService.getSession(email);
    }
            
    @RequestMapping("/")
    public String welcome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
        
        return "login";
    }
    
    @RequestMapping("/loginerror")
    public String loginerror(RedirectAttributes redirect) {
        redirect.addFlashAttribute("message", "Invalid Email and Password");
        return "redirect:/";
    }
    
    @RequestMapping("/logout")
    public String logout(RedirectAttributes redirect){
        redirect.addFlashAttribute("message","logout success");
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String handlinglog(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
    
    @PostMapping("/verifikasi")
    public String test(@ModelAttribute(value="email")String email,@ModelAttribute(value="password")String password){
        String result = "";
        EmployeeDummy employee = rest.login(email, password);
        System.out.println(employee.getStatus());
        if(employee.getStatus().equalsIgnoreCase("Berhasil")){
            User user = new User(employee.getId(), "", getAuthorities(employee));
            
            PreAuthenticatedAuthenticationToken authentication =  new PreAuthenticatedAuthenticationToken(user,"",user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            result="redirect:/employee";
        }
        else{
            result="redirect:/";
        }
        return result;
    }
    
    private static Collection<? extends GrantedAuthority> getAuthorities(EmployeeDummy employeeDummy){
        String roles[] = employeeDummy.getRoles();
        final List<SimpleGrantedAuthority> auth = new LinkedList<>();
        for (String role : employeeDummy.getRoles()) {
            auth.add(new SimpleGrantedAuthority(role));
        }
        return auth;
    }
}
