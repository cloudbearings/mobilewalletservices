/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.bo;

import com.mobilewallet.common.dao.RegisterDAO;

/**
 *
 * @author Gopi
 */
public class RegisterBO {

    private static final RegisterDAO REGISTER_DAO = new RegisterDAO();

    public static Object[] registerUser(String email, String fname, String lname, String dob, String gender, String pwd, String imei, String accounts, String country, String handsetModel, String androidVer, String emulator, String gcmId, String androidId, String refCode, String ip, String fbId) {
        return REGISTER_DAO.registerUser(email, fname, lname, dob, gender, pwd, imei, accounts, country, handsetModel, androidVer, emulator, gcmId, androidId, refCode, ip, fbId);
    }

    public static void updateGCM(String userId, String gcmId) {
        REGISTER_DAO.updateGCM(userId, gcmId);
    }
}
