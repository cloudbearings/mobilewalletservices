/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.FeedBackBO;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.mail.SendMail;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 *
 * @author sasidhar
 */
@Path("feedback")
public class FeedbackService {

    private Log log = LogFactory.getLog(FeedbackService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object feedBack(
            @Context HttpServletRequest request,
            @QueryParam("id") String eid,
            @QueryParam("feedType") String feedType,
            @QueryParam("text") String text,
            @QueryParam("email") String email) {

        log.info("User ID : " + eid);
        String decryptedUserId = MobileWalletID.getDecryptedUserId(eid);
        String userId = null;

        if (decryptedUserId != null) {
            try {
                userId = decryptedUserId.replace(Config.USER_ENCRYPTED_EXTENSION, "");
            } catch (Exception ex) {

            }
        }

        try {
            if (userId != null) {

                User user = LoginBO.login(userId);

                JSONObject obj = new JSONObject();

                if (user != null) {

                    FeedBackBO.feedBack(user.getUserId(), URLDecoder.decode(feedType, "UTF-8"), URLDecoder.decode(text, "UTF-8"), request.getRemoteAddr(), user.getEmail());
                    SendMail sm = new SendMail();
                    try {
                        //sm.sendHTMLMessage("Freeplus App Feed<noreply@amulyam.com>", "freeplusapp@gmail.com", user.getEmail(), user.getEmail() + ", " + feedType, "Email : " + user.getEmail() + "<br/>" + text);
                    } catch (Exception ex) {

                    }
                    try {
                        obj.put("sc", "Y");
                    } catch (Exception ex) {

                    }
                    return obj.toString();
                } else {
                    try {
                        obj.put("user", "invalid");
                    } catch (Exception ex) {

                    }
                    return obj.toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new LoginBean();
    }
}
