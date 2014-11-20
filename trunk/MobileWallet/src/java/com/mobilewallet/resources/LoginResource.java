/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 *
 * @author sasidhar
 */
@Path("login")
public class LoginResource {

    private static final Log log = LogFactory.getLog(LoginResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(@QueryParam("email") String email, @QueryParam("pwd") String password) {

        if (email != null && password != null) {

            log.info(" Email : " + email + ", PWD : " + password);

            JSONObject obj = new JSONObject();
            try {
                obj.put("id", "10000");
                obj.put("name", "gopi");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return obj.toString();
        }

        JSONObject obj = new JSONObject();
        try {
            obj.put("invalid", "Y");
        } catch (Exception ex) {

        }
        return obj.toString();
    }
}
