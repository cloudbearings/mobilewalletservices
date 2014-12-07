/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.common.bo.ReferralIncentiveBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletDE;
import com.mobilewallet.util.MobileWalletID;
import com.walletplus.util.SendNotification;
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
@Path("addReferralIncentive")
public class AddReferralIncentiveService {

    private final Log log = LogFactory.getLog(AddReferralIncentiveService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object addReferralIncentive(
            @Context HttpServletRequest request,
            @QueryParam("id") String encryptedId
    ) {

        String encryptedUserId = null;
        String refCode = null;
        String imei = null;
        String userId = null;

        log.info("EID : " + encryptedId);
        MobileWalletDE l = new MobileWalletDE("Friends Invitation code updated successfully.");
        String decryptedRegistrationInfo = l.decryptURLSafe(encryptedId);
        log.info("DID : " + decryptedRegistrationInfo);
        try {
            String[] regArray = decryptedRegistrationInfo.split("\\$ #");
            encryptedUserId = regArray[0];
            refCode = regArray[1];
            imei = regArray[2];
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String decryptedUserId = MobileWalletID.getDecryptedUserId(encryptedUserId);
        log.info("Decrypted User ID : " + decryptedUserId);

        if (decryptedUserId != null) {
            try {
                userId = decryptedUserId.replace(Config.USER_ENCRYPTED_EXTENSION, "");
            } catch (Exception ex) {

            }
        }

        if (userId != null) {

            User user = LoginBO.login(userId);

            if (user != null) {

                Object[] robj = null;
                String gcmId = null;
                String refAmount = null;
                int added = -1;

                robj = ReferralIncentiveBO.addReferralIncetive(user.getUserId(), refCode, imei, request.getRemoteAddr());
                if (robj != null) {
                    try {
                        added = (Integer) robj[0];
                        gcmId = (String) robj[1];
                        refAmount = (String) robj[2];
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                JSONObject obj = new JSONObject();
                try {
                    if (added == 0) {
                        //Success
                        obj.put("status", "Y");
                    } else {
                        obj.put("status", "N");
                    }
                } catch (Exception ex) {
                    log.error("Error in referral incentive service : " + ex.getMessage());
                }

                /* GCM PUSH NOTIFICATION STATRS*/
                try {
                    if (gcmId != null) {
                        SendNotification sn = new SendNotification();
                        sn.sendNotification(gcmId, Config.GCM_SERVER_ID, "$ " + refAmount + " added", "For inviting your friend \'" + user.getName() + "\'", "credit", "" + refAmount, "99", "eid", "url", "package");
                    }
                } catch (Exception ed) {

                }
                /* GCM PUSH NOTIFICATION ENDS*/
                return obj.toString();
            }
        }

        return new LoginBean();
    }
}
