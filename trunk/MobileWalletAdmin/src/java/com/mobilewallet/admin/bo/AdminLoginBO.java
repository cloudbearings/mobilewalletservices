/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.bo;

import com.mobilewallet.admin.dao.AdminLoginDAO;
import com.mobilewallet.admin.dto.AdminUser;

/**
 *
 * @author Gopi
 */
public class AdminLoginBO {

    private static final AdminLoginDAO adminLoginDAO = new AdminLoginDAO();

    public static AdminUser login(String email, String password) {
        return adminLoginDAO.login(email, password);
    }
}
