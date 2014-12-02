/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.bo.BalanceBO;
import com.mobilewallet.users.dto.Balance;
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
 * @author Gopi
 */
@Path("balance")
public class BalanceService {

    private final Log log = LogFactory.getLog(BalanceService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object balance(@QueryParam("id") String eid) {

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
                Balance w = BalanceBO.balanceInfo(user.getUserId());
                if (w != null) {
                    return w;
                }
            }
        }

        return new LoginBean();
    }
}
