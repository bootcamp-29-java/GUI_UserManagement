/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.controllers;

import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.Status;
import com.usermanagement.UserManagement.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.usermanagement.UserManagement.services.EmployeeServices;
import com.usermanagement.UserManagement.services.MaritalService;
import com.usermanagement.UserManagement.services.ReligionService;
import com.usermanagement.UserManagement.tools.AllMethod;
import javax.validation.Valid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Reza
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeServices services;

    @Autowired
    MaritalService Maritalservices;

    @Autowired
    ReligionService Religionservices;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    AccountService Accountservice;

    Account acc;

    @RequestMapping("/employee")
    public String getAll(Model model, Employee employee) {
        model.addAttribute("employees", services.getAll());

        model.addAttribute("maritals", Maritalservices.getAll());
        model.addAttribute("religions", Religionservices.getAll());
        return "index";
    }

    @PostMapping("/employeeSave")
    public String save(Model model, @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", services.getAll());
            model.addAttribute("maritals", Maritalservices.getAll());
            model.addAttribute("religions", Religionservices.getAll());

//            model.addAttribute(model)
        }
        System.out.println(employee.getId());
        System.out.println(employee.getFirstName());
        System.out.println(employee.getLastName());
        System.out.println(employee.getEmail());

        model.addAttribute("employees", services.getAll());

        model.addAttribute("maritals", Maritalservices.getAll());
        model.addAttribute("religions", Religionservices.getAll());

        model.addAttribute("employees", services.getAll());

        model.addAttribute("maritals", Maritalservices.getAll());
        model.addAttribute("religions", Religionservices.getAll());

        services.save(employee);
        Accountservice.save(saveAccount(employee.getId(), "-1"));

        return "redirect:/employee";
    }

//    @GetMapping("/sendMail")
//    public String send(String email) {
//        String token = AllMethod.generateToken();
//        sendEmail(email, "localhost:8085/sendMail", token);
//        
//        Accountservice.save(acc);
//        
//        return "redirect:/employee";
//    }
    
    @GetMapping("/employeeDelete")
    public String delete(String id) {
        services.delete(id);
        return "redirect:/employee";
    }
    
//    @GetMapping("/sendMail/{token}")
//    public String ResetPassword(Model model,@PathVariable(value="token")String token){
//                
//        model.addAttribute("token",token);
//
//        
//        return "resetPassword";
//    }
//    
//    @GetMapping("/sendMail")
//    public String send(Model model,String email) {
//        Employee emp = new Employee();
//        
//        String token = AllMethod.generateToken();
//        String temp = token;
//        model.addAttribute("token",temp);
//        Accountservice.save(saveAccount(emp.getId(),"-1",temp));
//        sendEmail(email, "http://localhost:8085/sendMail", temp);
//        
//        return "redirect:/employee";
//    }
//
//    public void sendEmail(String email, String link, String token) {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(email);
//
//        msg.setSubject("Activation Account");
//        msg.setText("Click Link For Activation Your Account " + link + "/" + token);
//
//        javaMailSender.send(msg);
//
//    }
    
    public Account saveAccount(String id, String status){
        Account acc = new Account();
        Status stt = new Status();
        acc.setId(id);
        stt.setId(status);
        acc.setStatus(stt);
        
        return acc;
    }
}
