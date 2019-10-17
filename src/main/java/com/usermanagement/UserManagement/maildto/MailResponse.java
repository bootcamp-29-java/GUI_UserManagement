/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usermanagement.UserManagement.maildto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Reza
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailResponse {

    private String message;
    private boolean status;
}
