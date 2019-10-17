/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.controllers;

import com.usermanagement.UserManagement.configuration.BCrypt;
import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.entities.Role;
import com.usermanagement.UserManagement.entities.Status;
import com.usermanagement.UserManagement.repositories.AccountRepository;
import com.usermanagement.UserManagement.repositories.EmployeeRepository;
import com.usermanagement.UserManagement.services.AccountService;
import com.usermanagement.UserManagement.services.EmployeeServices;
import com.usermanagement.UserManagement.services.RoleServices;
import com.usermanagement.UserManagement.tools.AllMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
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
public class AccountController {

    @Autowired
    AccountService services;

    @Autowired
    EmployeeServices employeeServices;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/account")
    public String getAll(Model model, Account account) {
        model.addAttribute("accounts", services.getAll());
        model.addAttribute("emloyees", employeeServices.getAll());

        return "account";
    }

    @PostMapping("/accountSave")
    public String save(Model model, @Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", services.getAll());

            model.addAttribute("emloyees", employeeServices.getAll());
        }
        services.save(account);
        model.addAttribute("accounts", services.getAll());

        model.addAttribute("emloyees", employeeServices.getAll());

        model.addAttribute("accounts", services.getAll());

        model.addAttribute("emloyees", employeeServices.getAll());
        return "redirect:/accounts";
    }

//    @GetMapping("/accountToken")
//    public String getByToken(String token) {
//        services.getByToken(token);
//
//        return "";
//    }
    @GetMapping("/accountDelete")
    public String delete(String id) {
        services.delete(null);
        return "redirect:/account";
    }

    @GetMapping("/sendMail/{token}")
    public String ResetPassword(Model model, @PathVariable(value = "token") String token, Account account) {

        model.addAttribute("token", token);
//        model.addAttribute("account",services.getAll());

        return "resetPassword";
    }

    @PostMapping("/cekEmail")
    public String cekEmail(Model model, HttpServletRequest request) {
        String email = request.getParameter("email");

        Employee employee = employeeServices.getByEmail(email);

        if (email.equalsIgnoreCase(employee.getEmail())) {
            String token = AllMethod.generateToken();
            String temp = token;
            sendEmail(email, "http://localhost:8085/sendMail", temp);
            services.save(saveAccount(employee.getId(), temp, "-1"));
            return "redirect:/";
        } else {
            model.addAttribute("Kesalahan", "Silahkan Masukan Email Dengan Benar");
            return "redirect:/";
        }
    }

    @PostMapping("/updatePassword")
    public String updatePassword(HttpServletRequest request, Account account) {

        String result = "";
        String pass = request.getParameter("password");
        String token = request.getParameter("token");

        System.out.println(token);
        Account acc = services.getByToken(token);
        System.out.println(services.getByToken(token));
        System.out.println(acc.getId());
        services.save(Update(acc.getId(), "0", pass, ""));
        return "redirect:/";
    }

    @GetMapping("/sendMail")
    public String send(Model model, String email, String id) {
        String token = AllMethod.generateToken();
        String temp = token;
        services.save(saveAccount(id, temp, "-1"));
        model.addAttribute("token", temp);
        sendEmail(email, "http://localhost:8085/sendMail", temp);

        return "redirect:/account";
    }

    public void sendEmail(String email, String link, String token) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Activation Account");
        msg.setText("Click Link For Activation Your Account " + link + "/" + token);

        javaMailSender.send(msg);

    }

    public Account saveAccount(String id, String token, String status) {
        Account acc = new Account();
        Status stat = new Status();
        acc.setId(id);
        acc.setToken(token);
        stat.setId(status);
        acc.setStatus(stat);

        return acc;

    }

    public Account Update(String id, String status, String Password, String token) {
        Account acc = new Account();
        Status stat = new Status();
        acc.setId(id);
        stat.setId(status);
        acc.setStatus(stat);
        acc.setPassword(BCrypt.hashpw(Password, BCrypt.gensalt()));
        acc.setToken(token);

        return acc;
    }
}
