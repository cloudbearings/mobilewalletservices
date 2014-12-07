/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 *
 * @author Gopi
 */
@Path("login")
public class LoginService {

    private static final Log log = LogFactory.getLog(LoginService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(@QueryParam("email") String email, @QueryParam("pwd") String password) {

        if (email != null && password != null) {

            String salt = Config.PWD_SALT + password;
            String md5PWD = org.apache.commons.codec.digest.DigestUtils.md5Hex(password + salt);
            User user = LoginBO.login(email.toLowerCase(), md5PWD);
            log.info(" Email : " + email + ", PWD : " + password);
            if (user != null) {
                log.info("Amount : " + user.getAmount() + ", Email : " + email);
                String userEID = MobileWalletID.getEncryptedUserId(Config.USER_ENCRYPTED_EXTENSION + user.getUserId());
                JSONObject obj = new JSONObject();
                try {
                    obj.put("userId", userEID);
                    obj.put("name", user.getName());
                    obj.put("amount", Float.toString(user.getAmount()));
                    obj.put("mycode", user.getMyRefCode());
                    log.info("JSON Amount : " + obj.getString("amount"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return obj.toString();
            }
        }

        JSONObject obj = new JSONObject();
        try {
            obj.put("invalid", "Y");
        } catch (Exception ex) {

        }
        return obj.toString();
    }
}
