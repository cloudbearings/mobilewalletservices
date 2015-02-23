/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.bo.UserBO;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author sasidhar
 */
@Path("updateProfile")
public class UpdateUserProfileService {

    private final Log log = LogFactory.getLog(UpdateUserProfileService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateProfile(
            @QueryParam("id") String eid,
            @QueryParam("mCode") String mCode,
            @QueryParam("mobileNumber") String mobileNumber,
            @QueryParam("dob") String dob,
            @QueryParam("gender") String gender,
            @QueryParam("occupation") String occupation,
            @QueryParam("income") String income) {

        String decryptedUserId = MobileWalletID.getDecryptedUserId(eid);
        String userId = null;

        if (decryptedUserId != null) {
            try {
                userId = decryptedUserId.replace(Config.USER_ENCRYPTED_EXTENSION, "");
            } catch (Exception ex) {

            }
        }
        log.info("UserId : " + userId);
        if (userId != null) {
            User user = LoginBO.login(userId);

            if (user != null) {
                return UserBO.updateProfile(user.getUserId(), mCode, mobileNumber, dob, gender, occupation, income);
            }
        }

        return new LoginBean();
    }
}
