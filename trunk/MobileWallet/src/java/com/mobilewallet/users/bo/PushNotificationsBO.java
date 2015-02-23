/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.bo;

import com.mobilewallet.users.dao.PushNotificationsDAO;

/**
 *
 * @author sasidhar
 */
public class PushNotificationsBO {

    private static final PushNotificationsDAO PUSH_NOTIFICATION_DAO = new PushNotificationsDAO();

    public static int updateNotification(long userId, String status, String type) {
        return PUSH_NOTIFICATION_DAO.updateNotification(userId, status, type);
    }

    public static String getNotificationStatusOfUser(long userId) {
        return PUSH_NOTIFICATION_DAO.getNotificationStatusOfUser(userId);
    }
}
