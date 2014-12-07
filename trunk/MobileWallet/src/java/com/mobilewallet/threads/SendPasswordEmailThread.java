/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.threads;

import com.mobilewallet.mail.SendMail;

/**
 *
 * @author Gopi
 */
public class SendPasswordEmailThread implements Runnable {

    private final String password;
    private final String email;
    private final String fname;
    private final String lname;

    public SendPasswordEmailThread(String email, String password, String fname, String lname) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
    }

    @Override
    public void run() {
        try {
            String body
                    = "      <br/> "
                    + " Hi " + fname + " " + lname + ",<br/><br/> "
                    + "  Your MobileWallet password : " + password
                    + " Thanks, <br/> "
                    + " MobileWallet. ";

            String from = "MobileWallet <noreply@mobilewalletapp.com>";
            String subject = "Your MobileWallet password";
            new SendMail().sendHTMLMessage(from, email, subject, body);
        } catch (Exception ex) {
        }
    }
}
