/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.bo.PushNotificationsBO;
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
 * @author gopi
 */
@Path("updateNotifications")
public class NotificationsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object pushNotification(@QueryParam("id") String eid, @QueryParam("status") String status, @QueryParam("type") String type) {

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
            try {
                JSONObject obj = new JSONObject();
                if (user != null) {
                    int updated = PushNotificationsBO.updateNotification(user.getUserId(), status, type);
                    obj.put("sc", "Y");
                    return obj.toString();
                } else {
                    return new LoginBean();
                }
            } catch (Exception ex) {

            }
        }

        return new LoginBean();

    }
}
