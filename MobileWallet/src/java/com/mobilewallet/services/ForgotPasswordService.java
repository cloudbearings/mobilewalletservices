/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.services;

import com.mobilewallet.threads.ForgotPasswordEmailThread;
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
 * @author Gopi
 */
@Path("forgotPassword")
public class ForgotPasswordService {

    private static final Log log = LogFactory.getLog(ForgotPasswordService.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object sendPassword(@Context HttpServletRequest request, @QueryParam("email") String email) {
        log.info("Forgot Password Email : " + email);

        try {
            ForgotPasswordEmailThread sv = new ForgotPasswordEmailThread(email.toLowerCase(), request.getRemoteAddr());
            Thread t = new Thread(sv);
            t.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("sent", true);
        } catch (Exception ex) {

        }
        return obj.toString();
    }
}
