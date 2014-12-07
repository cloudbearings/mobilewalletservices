/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walletplus.util;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 *
 * @author Gopi
 */
public class SendNotification {

    private Log log = LogFactory.getLog(SendNotification.class);

    public void sendNotification(String regId, String apiKey, String title, String desc, String type, String amount, String id, String eid, String url, String vpackage) {
        try {
            JSONObject dataObj = new JSONObject();
            dataObj.put("type", type);
            dataObj.put("title", title);
            dataObj.put("desc", desc);
            dataObj.put("amount", amount);
            dataObj.put("sid", id);
            dataObj.put("eid", eid);
            dataObj.put("url", url);
            dataObj.put("package", vpackage);

            String data = dataObj.toString();
            Sender sender = new Sender(apiKey);

            Message message = new Message.Builder()
                    .collapseKey(id)
                    .addData("message", data).build();
            Result r = sender.send(message, regId, 2);
            log.info("Error : " + r.getErrorCodeName());
            log.info("MSG : " + r.getMessageId());
        } catch (Exception ex) {

        }
    }
}
