/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.dao;


import com.mobilewallet.connection.ConnectionUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gopi
 */
public class NotificationsDAO {

    private Log log = LogFactory.getLog(NotificationsDAO.class);
    private DataSource dataSource;

    private static final String getUserNotificationsQuery = "select u_notify from users where u_id = ?";

    public NotificationsDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public int updateNotification(long userId, String status, String type) {
        int updated = 0;
        Connection connection = null;
        CallableStatement cstmt = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call wp_update_push_notification(?,?,?,?)}");
            cstmt.setLong(1, userId);
            cstmt.setString(2, status);
            cstmt.setString(3, type);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.execute();

            updated = cstmt.getInt(4);
        } catch (Exception ex) {

        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
            } catch (Exception ex) {

            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {

            }
        }
        return updated;
    }

    public String getNotificationStatusOfUser(long userId) {
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        String s = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(getUserNotificationsQuery);
            cstmt.setLong(1, userId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                s = rs.getString("u_notify");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {

            }
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
            } catch (Exception ex) {

            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {

            }
        }
        return s;
    }
}
