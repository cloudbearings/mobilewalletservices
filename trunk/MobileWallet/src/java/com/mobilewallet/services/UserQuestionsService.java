/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.common.beans.LoginBean;
import com.mobilewallet.common.bo.LoginBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.users.beans.UserQuestionsBean;
import com.mobilewallet.users.bo.UserQuestionsBO;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.users.dto.UserQuestionsDTO;
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
@Path("userQuestions")
public class UserQuestionsService {

    private final Log log = LogFactory.getLog(UserQuestionsService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object profile(@QueryParam("userId") String eid, @QueryParam("b") String b) {

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
                UserQuestionsBean userQuestions = null;
                int count = UserQuestionsBO.userQuestionsCount(user.getUserId());

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

                    List<UserQuestionsDTO> userQueList = UserQuestionsBO.userQuestions(user.getUserId(), begin, end);

                    if (userQueList != null && !userQueList.isEmpty()) {
                        userQuestions = new UserQuestionsBean();
                        userQuestions.setCtrList(userQueList);
                        userQuestions.setCount(count);
                        userQuestions.setBegin(begin);
                        userQuestions.setEnd(end);
                        return userQuestions;
                    } else {
                        userQuestions = new UserQuestionsBean();
                        userQuestions.setNon("Y");
                        return userQuestions;
                    }

                } else {
                    userQuestions = new UserQuestionsBean();
                    userQuestions.setNon("Y");
                    return userQuestions;
                }
            }
        }

        return new LoginBean();
    }
}
