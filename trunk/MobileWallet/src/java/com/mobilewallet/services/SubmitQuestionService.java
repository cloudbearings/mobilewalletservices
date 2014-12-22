/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.bo.UserQuestionsBO;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
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
@Path("submitQuestion")
public class SubmitQuestionService {

    private Log log = LogFactory.getLog(FeedbackService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object feedBack(
            @Context HttpServletRequest request,
            @QueryParam("userId") String id,
            @QueryParam("question") String question,
            @QueryParam("ansewrA") String ansewrA,
            @QueryParam("ansewrB") String ansewrB, @QueryParam("ansewrC") String ansewrC, @QueryParam("ansewrD") String ansewrD, @QueryParam("ansewr") String ansewr) {

        log.info("User ID : " + id);
        String decryptedUserId = MobileWalletID.getDecryptedUserId(id);
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

                    int submitted = UserQuestionsBO.submitQuestion(Long.parseLong(userId), question, ansewrA, ansewrB, ansewrC, ansewrD, ansewr);
                    try {
                        if (submitted == 0) {
                            obj.put("status", "Y");
                        } else {
                            obj.put("status", "N");
                        }
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
