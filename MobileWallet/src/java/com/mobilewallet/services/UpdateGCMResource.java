/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.common.bo.RegisterBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author Gopi
 */
@Path("updateGCM")
public class UpdateGCMResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateGCM(@QueryParam("id") String eid, @QueryParam("gcmid") String gcmid) {

        String decryptedUserId = MobileWalletID.getDecryptedUserId(eid);
        String userId = null;

        if (decryptedUserId != null) {
            try {
                userId = decryptedUserId.replace(Config.USER_ENCRYPTED_EXTENSION, "");
            } catch (Exception ex) {

            }
        }

        if (userId != null) {

            User user = LoginBO.login(userId);

            if (user != null) {
                JSONObject obj = new JSONObject();
                try {
                    RegisterBO.updateGCM(Long.toString(user.getUserId()), gcmid);

                    obj.put("updated", "Y");
                } catch (Exception ex) {

                }
                return obj.toString();
            }
        }
        return new LoginBean();
    }
}
