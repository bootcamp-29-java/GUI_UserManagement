/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.controllers;

import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.Role;
import com.usermanagement.UserManagement.services.RoleServices;
import com.usermanagement.UserManagement.repositories.RoleRepository;
import javax.servlet.http.HttpServletRequest;
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
public class RoleController {

    @Autowired
    RoleServices services;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/role")
    public String getAll(Model model, Role role) {
        model.addAttribute("roles", services.getAll());
        model.addAttribute("idRole", roleRepository.genIdRole());
        return "role";
    }

    @PostMapping("/roleSave")
    public String save(Model model, @Valid Role role, BindingResult bindingResult) {
//    public String save(HttpServletRequest request,Model model,@Valid Role role, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", services.getAll());
        } else {

            services.save(role);

            model.addAttribute("idRole", roleRepository.genIdRole());
            model.addAttribute("roles", services.getAll());
        }
        model.addAttribute("roles", services.getAll());

        model.addAttribute("idRole", roleRepository.genIdRole());
        return "redirect:/role";
    }

    @GetMapping("/roleDelete")
    public String delete(String id) {
        services.delete(id);
        return "redirect:/role";
    }

}
