/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.ForgotPasswordDAO;

/**
 *
 * @author Gopi
 */
public class ForgotPasswordBO {

    private static final ForgotPasswordDAO FORGOT_PASSWORD_DAO = new ForgotPasswordDAO();

    public static Object[] getResetPasswordLink(String email, String uuid, String ip) {
        return FORGOT_PASSWORD_DAO.getResetPasswordLink(email, uuid, ip);
    }

    public static int checkResetLink(String uuid, String userId, String ip) {
        return FORGOT_PASSWORD_DAO.checkResetLink(uuid, userId, ip);
    }

    public static int resetPassword(String uuid, String userId, String password, String ip) {
        return FORGOT_PASSWORD_DAO.resetPassword(uuid, userId, password, ip);
    }
}
