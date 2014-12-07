/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.threads;

import com.mobilewallet.common.bo.ForgotPasswordBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.mail.SendMail;
import com.mobilewallet.util.MobileWalletDE;

/**
 *
 * @author Gopi
 */
public class ForgotPasswordEmailThread implements Runnable {

    private String email, ip;

    public ForgotPasswordEmailThread() {

    }

    public ForgotPasswordEmailThread(String email, String ip) {
        this.email = email;
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
            if (email != null) {
                MobileWalletDE f = new MobileWalletDE(Config.RESET_PWD_KEY);
                String uuid = java.util.UUID.randomUUID().toString();
                int rvalue = -1;
                long userId = 0;
                email = f.decryptURLSafe(email);
                Object[] obj = ForgotPasswordBO.getResetPasswordLink(email.toLowerCase(), uuid, ip);
                if (obj != null) {
                    try {
                        rvalue = (Integer) obj[0];
                        userId = (Long) obj[1];
                    } catch (Exception ex) {

                    }
                }
                if (rvalue == 0 && userId != 0) {

                    String body
                            = "      <br/> "
                            + " Hi ,<br/><br/> "
                            + " You recently asked to reset your MobileWallet app password. To complete your request, please follow this link:<br/><br/> "
                            + " http://www.mobilewalletapp.com/resetpassword/" + f.encryptURLSafe(uuid + "$ #" + userId) + "<br/><br/> "
                            + " Note: The above URL works only once. Please change your password immediately after clicking the above link.<br/><br/> "
                            + " In case of any assistance, contact <a style=\"font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 9px;color: #000000; \" href=\"mailto:mobilewalletapp@gmail.com\">mobilewalletapp@gmail.com</a>. <br/><br/> "
                            + " Thanks, <br/> "
                            + " MobileWallet. ";

                    String from = "MobileWallet <noreply@mobilewalletapp.com>";
                    String subject = "Reset Your MobileWalletApp Password";
                    new SendMail().sendHTMLMessage(from, email, subject, body);
                }
            }
        } catch (Exception ex) {
        }
    }
}
