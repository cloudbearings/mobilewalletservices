/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.bo;

import com.mobilewallet.users.dao.NotificationsDAO;

/**
 *
 * @author Gopi
 */
public class NotificationsBO {

    private static final NotificationsDAO NOTIFICATION_DAO = new NotificationsDAO();

    public static int updateNotification(long userId, String status, String type) {
        return NOTIFICATION_DAO.updateNotification(userId, status, type);
    }

    public static String getNotificationStatusOfUser(long userId) {
        return NOTIFICATION_DAO.getNotificationStatusOfUser(userId);
    }
}
