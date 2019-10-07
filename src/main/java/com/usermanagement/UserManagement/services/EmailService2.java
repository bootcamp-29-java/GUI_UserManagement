/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author Reza
 */
public class EmailService2 {
    
    @Autowired JavaMailSender javaMailSender;
    
    void SendMail(){
        
        SimpleMailMessage micin = new SimpleMailMessage();
    }
}
