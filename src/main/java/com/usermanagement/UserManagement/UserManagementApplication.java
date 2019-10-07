package com.usermanagement.UserManagement;

import com.usermanagement.UserManagement.controllers.EmployeeController;
import com.usermanagement.UserManagement.entities.Account;
import com.usermanagement.UserManagement.entities.Employee;
import com.usermanagement.UserManagement.maildto.MailRequest;
import com.usermanagement.UserManagement.maildto.MailResponse;
import com.usermanagement.UserManagement.services.AccountService;
import com.usermanagement.UserManagement.services.EmailService;
import com.usermanagement.UserManagement.services.EmployeeServices;
import com.usermanagement.UserManagement.tools.AllMethod;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UserManagementApplication  {

    public static void main(String[] args)  {
        SpringApplication.run(UserManagementApplication.class, args);
        
        
        System.out.println("test aja");
    }

}
