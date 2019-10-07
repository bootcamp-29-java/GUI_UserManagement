/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usermanagement.UserManagement.entities.EmployeeDummy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Reza
 */
@Service
@Transactional
public class LoginRest {
    
    
    private static RestTemplate resttemplate=new RestTemplate();
    
    public EmployeeDummy login(String email, String password){
        MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("email", email);
        param.add("password", password);
        
        HttpHeaders head = new HttpHeaders();
        
        head.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param,head);
        
        ResponseEntity<EmployeeDummy>responseEntity = resttemplate.exchange("http://192.168.128.254/login/", 
                HttpMethod.POST,entity,EmployeeDummy.class);
        EmployeeDummy result = responseEntity.getBody();
        
        return result;
    }
}
