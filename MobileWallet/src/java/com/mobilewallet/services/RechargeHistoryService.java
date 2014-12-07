/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.credits.bo.CreditsBO;
import com.mobilewallet.credits.dto.RechargeHistoryBean;
import com.mobilewallet.credits.dto.RechargeHistoryDTO;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.util.MobileWalletID;
import java.util.List;
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
@Path("rechargeHistory")
public class RechargeHistoryService {

    private final Log log = LogFactory.getLog(RechargeHistoryService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object creditHistory(@QueryParam("id") String eid, @QueryParam("b") String b) {

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

                RechargeHistoryBean crhb = null;

                int count = CreditsBO.debitCount(user.getUserId());

                int begin = 0;
                try {
                    begin = Integer.parseInt(b);
                } catch (Exception ex) {

                }

                if (count > 0) {

                    if (begin == 0) {
                        begin = 1;
                    }

                    if ((begin % 15 != 1) || (begin > count)) {
                        begin = 1;
                    }

                    int end = begin + 14;
                    if (end > count) {
                        end = count;
                    }

                    List<RechargeHistoryDTO> ctrList = CreditsBO.debitHistory(user.getUserId(), begin, end);

                    if (ctrList != null && !ctrList.isEmpty()) {
                        crhb = new RechargeHistoryBean();
                        crhb.setDtrList(ctrList);
                        crhb.setCount(count);
                        crhb.setBegin(begin);
                        crhb.setEnd(end);
                        return crhb;
                    } else {
                        crhb = new RechargeHistoryBean();
                        crhb.setNon("Y");
                        return crhb;
                    }

                } else {
                    crhb = new RechargeHistoryBean();
                    crhb.setNon("Y");
                    return crhb;
                }
            }
        }
        return new LoginBean();
    }
}
