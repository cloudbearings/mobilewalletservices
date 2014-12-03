/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mobilewallet.common.beans.DailyRewardsBean;
import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.DailyRewardsBO;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.GenerateUID;
import com.mobilewallet.util.MobileWalletDE;
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
@Path("dailyCredits")
public class DailyRewardsService {

    private Log log = LogFactory.getLog(DailyRewardsService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object dailyRewards(@Context HttpServletRequest request,
            @QueryParam("id") String eid) {

        String imei = null;
        String gcmId = null;
        String userId = null;

        try {
            MobileWalletDE e = new MobileWalletDE("Added Daily Credits in your wallet.");
            String decryptedID = e.decryptURLSafe(eid);
            String[] params = decryptedID.split("\\$ #");
            String decryptedUserId = MobileWalletID.getDecryptedUserId(params[0]);
            if (decryptedUserId != null) {
                try {
                    userId = decryptedUserId.replace(Config.USER_ENCRYPTED_EXTENSION, "");
                } catch (Exception ex) {

                }
            }
            imei = params[1];
            gcmId = params[2];

        } catch (Exception ex) {

        }

        if (userId != null) {

            User user = LoginBO.login(userId);

            if (user != null) {
                int rvalue = DailyRewardsBO.addDailyRewards(user.getUserId(), request.getRemoteAddr(), imei);
                log.info("Daily Rewards Rvalue : " + rvalue);
                if (rvalue == 0) {

                    //Send GCM Push Notification starts
                    try {
                        JSONObject dataObj = new JSONObject();
                        dataObj.put("type", "dailyRewards");
                        dataObj.put("title", "$0.15 added");
                        dataObj.put("desc", "Daily rewards");
                        dataObj.put("amount", "0.15");

                        String data = dataObj.toString();
                        Sender sender = new Sender("AIzaSyBLAOakCF-8CvQFZ8nQjyxxs5yF0aS-BNw");

                        Message message = new Message.Builder()
                                .collapseKey(GenerateUID.getCollapaceKey())
                                .addData("message", data).build();
                        Result r = sender.send(message, gcmId, 2);
                    } catch (Exception ex) {

                    }
                    //Send GCM Push Notification ends

                    return new DailyRewardsBean("Y");
                } else {
                    return new DailyRewardsBean("N");
                }
            } else {
                return new LoginBean();
            }
        } else {
            return new LoginBean();
        }
    }
}
