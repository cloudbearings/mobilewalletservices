/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.bo.RegisterBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.threads.SendPasswordEmailThread;
import com.mobilewallet.util.MobileWalletDE;
import com.mobilewallet.util.MobileWalletID;
import com.walletplus.util.Password;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Gopi
 */
@Path("register")
public class RegisterService {

    private final Log log = LogFactory.getLog(RegisterService.class);

    /**
     * Creates a new instance of GenericResource
     */
    public RegisterService() {
    }

    /**
     * Retrieves representation of an instance of com.wallet.GenericResource
     *
     * @param request
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String register(@Context HttpServletRequest request, @QueryParam("id") String id) {

        String imei = null;
        String country = null;
        String handsetModel = null;
        String androidVer = null;
        String accounts = null;
        String fname = null;
        String lname = null;
        String password = null;
        String email = null;
        String emulator = null;
        String gcmid = null;
        String refCode = null;
        String androidId = null;
        String gender = null;
        String dob = null;
        String fbId = null;

        if (id != null) {
            try {
                MobileWalletDE e = new MobileWalletDE("Please Enter Password.");
                String decryptedID = e.decryptURLSafe(id);
                String[] params = decryptedID.split("\\$ #");
                String encyptedEmail = params[0];
                e = new MobileWalletDE("Invalid Email.");
                email = e.decryptURLSafe(encyptedEmail);

                fname = params[1];
                lname = params[2];

                String enPassword = params[3];
                e = new MobileWalletDE("Invalid Password.");
                password = e.decryptURLSafe(enPassword);

                gender = params[4];
                dob = params[5];
                String enImei = params[6];
                e = new MobileWalletDE("Please Enter Username.");
                imei = e.decryptURLSafe(enImei);
                accounts = params[7];
                country = params[8];
                handsetModel = params[9];
                androidVer = params[10];
                emulator = params[11];
                gcmid = params[12];
                androidId = params[13];
                refCode = params[14];
                fbId = params[15];
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (email != null) {
            boolean sendEmailForPassword = false;
            if ("non".equals(password) || !"non".equals(fbId)) {
                //generate password and send it over email
                password = Password.getPassword();
                sendEmailForPassword = true;
                log.error("Password : " + password + ", Email : " + email);

            }
            if ("non".equals(gender)) {
                gender = null;
            }
            if ("non".equals(dob)) {
                dob = null;
            }
            if ("non".equals(lname)) {
                lname = null;
            }
            String salt = Config.PWD_SALT + password;
            String md5PWD = org.apache.commons.codec.digest.DigestUtils.md5Hex(password + salt);
            email = email.toLowerCase();
            Object[] obj = RegisterBO.registerUser(email, fname, lname, dob, gender, md5PWD, imei, accounts, country, handsetModel, androidVer, emulator, gcmid, androidId, refCode, request.getRemoteAddr(), fbId);
            int rvalue = -1;
            if (obj != null) {
                rvalue = (Integer) obj[0];
                try {
                    if (rvalue == 0 || rvalue == 2) {
                        String myCode = (String) obj[1];
                        Float balance = (Float) obj[2];
                        Long userId = (Long) obj[3];
                        String userEID = MobileWalletID.getEncryptedUserId(Config.USER_ENCRYPTED_EXTENSION + userId);

                        if (rvalue != 2) {
                            try {
                                SendPasswordEmailThread s = new SendPasswordEmailThread(email, password, fname, lname);
                                Thread t = new Thread(s);
                                t.start();
                            } catch (Exception ex) {
                            }
                        }
                        JSONObject jobj = new JSONObject();
                        jobj.put("id", userEID);
                        jobj.put("myRefCode", myCode);
                        jobj.put("BL", balance);
                        if (rvalue == 2) {
                            jobj.put("FB", "Y");
                        }
                        return jobj.toString();
                    } else {
                        log.error("Error while registering wallet user. Error Code : " + rvalue + ", Email : " + email);
                        JSONObject jobj = new JSONObject();
                        if (rvalue == 1) {
                            jobj.put("error", "Email already exisits.");
                        } else {
                            jobj.put("error", "Registration Failed.");
                        }
                        return jobj.toString();
                    }
                } catch (Exception ex) {
                }
            }
        } else {
            log.error("Email is null: " + email);
        }
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("error", "");
        } catch (Exception ex) {

        }
        return jobj.toString();
    }
}
