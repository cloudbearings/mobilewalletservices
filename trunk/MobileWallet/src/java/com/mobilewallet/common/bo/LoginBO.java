/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.LoginDAO;
import com.mobilewallet.users.dto.User;

/**
 *
 * @author gopi
 */
public class LoginBO {

    private static final LoginDAO LOGIN_DAO = new LoginDAO();

    public static User login(String userId) {
        return LOGIN_DAO.login(userId);
    }

    public static User login(String email, String password) {
        return LOGIN_DAO.login(email, password);
    }

    public static String getUserPassword(String email, String ip) {
        return LOGIN_DAO.getUserPassword(email, ip);
    }
}
