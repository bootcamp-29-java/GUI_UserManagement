/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.controllers;

import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.EmployeeRole;
import com.usermanagement.UserManagement.entities.Role;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import com.usermanagement.UserManagement.repositories.EmployeeRoleRepository;
import com.usermanagement.UserManagement.services.EmployeeServices;
import com.usermanagement.UserManagement.services.EmployeeRoleService;
import com.usermanagement.UserManagement.services.RoleServices;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Reza
 */
@Controller
public class EmployeeRoleController {

    @Autowired
    EmployeeRoleService services;

    @Autowired
    EmployeeServices employeeservices;

    @Autowired
    RoleServices roleservices;
    
    @Autowired
    EmployeeRoleRepository employeeRoleRepository;

    @RequestMapping("/employeerole")
    public String getAll(Model model, EmployeeRole employeerole) {
        model.addAttribute("employeeroles", services.getAll());
        model.addAttribute("roles", roleservices.getAll());
        model.addAttribute("employees", employeeservices.getAll());
        String id = employeeRoleRepository.genIdEmployeeRole();        
        model.addAttribute("idEmployeeRole",id);

        return "employeerole";
    }

    @PostMapping("/employeeroleSave")
    public String save(Model model, @Valid EmployeeRole employeerole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeroles", services.getAll());

            model.addAttribute("roles", roleservices.getAll());
            model.addAttribute("employees", employeeservices.getAll());
        }

        services.save(employeerole);
        String id = employeeRoleRepository.genIdEmployeeRole();
        model.addAttribute("idEmployeeRole",id);
        
        model.addAttribute("idEmployeeRole",id);
        model.addAttribute("employeeroles", services.getAll());

        model.addAttribute("roles", roleservices.getAll());
        model.addAttribute("employees", employeeservices.getAll());

        model.addAttribute("employeeroles", services.getAll());

        model.addAttribute("roles", roleservices.getAll());
        model.addAttribute("employees", employeeservices.getAll());
        return "redirect:/employeerole";
    }

    @GetMapping("/employeeroleDelete")
    public String delete(String id) {
        
        int pasr = Integer.parseInt(id);
        services.delete(Integer.parseInt(id));
        return "redirect:/employeerole";
    }
}
