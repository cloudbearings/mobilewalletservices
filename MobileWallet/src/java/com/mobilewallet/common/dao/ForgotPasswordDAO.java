/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.dao;

import com.mobilewallet.connection.ConnectionUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author sasidhar
 */
public class ForgotPasswordDAO {

    private Log log = LogFactory.getLog(ForgotPasswordDAO.class);
    private DataSource dataSource;

    public ForgotPasswordDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public Object[] getResetPasswordLink(String email, String uuid, String ip) {
        Connection connection = null;
        CallableStatement cstmt = null;
        Object[] obj = null;
        int rvalue = -1;
        long userId = 0;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call wp_forgot_pwd_reset_link(?,?,?,?,?)}");
            cstmt.setString(1, email);
            cstmt.setString(2, uuid);
            cstmt.setString(3, ip);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);

            cstmt.execute();

            rvalue = cstmt.getInt(4);
            userId = cstmt.getLong(5);

            obj = new Object[2];
            obj[0] = rvalue;
            obj[1] = userId;
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return obj;
    }

    public int checkResetLink(String uuid, String userId, String ip) {
        Connection connection = null;
        CallableStatement cstmt = null;
        int rvalue = -1;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call wp_check_reset_link(?,?,?,?)}");
            cstmt.setString(1, userId);
            cstmt.setString(2, uuid);
            cstmt.setString(3, ip);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);

            cstmt.execute();

            rvalue = cstmt.getInt(4);
            log.info("Rvalue Check ResetLink : " + rvalue);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return rvalue;
    }

    public int resetPassword(String uuid, String userId, String password, String ip) {
        Connection connection = null;
        CallableStatement cstmt = null;
        int rvalue = -1;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call wp_reset_pwd(?,?,?,?,?)}");
            cstmt.setString(1, userId);
            cstmt.setString(2, uuid);
            cstmt.setString(3, password);
            cstmt.setString(4, ip);
            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);

            cstmt.execute();

            rvalue = cstmt.getInt(5);

        } catch (Exception ex) {
            ex.printStackTrace();
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
        return rvalue;
    }
}
