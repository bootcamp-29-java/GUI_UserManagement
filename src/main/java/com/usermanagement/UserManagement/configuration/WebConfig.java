/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Reza
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("coba");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/employee").setViewName("employee");
        registry.addViewController("/role").setViewName("role");
        registry.addViewController("/employeerole").setViewName("employeerole");
        
    }
    
    
    
}
