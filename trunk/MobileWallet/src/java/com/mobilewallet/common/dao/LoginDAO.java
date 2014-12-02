/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.dao;

import com.mobilewallet.connection.ConnectionUtil;
import com.mobilewallet.users.dto.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author gopi
 */
public class LoginDAO {

    private Log log = LogFactory.getLog(LoginDAO.class);
    private DataSource dataSource;

    private static final String loginQuery = ""
            + "select u_id, u_email, u_name,  u_my_ref_code, u_friend_ref_code, w_amount "
            + "from users, wallet "
            + "where u_id = w_u_id and u_id = ?";
    private static final String loginEmailAndPwdQuery = ""
            + "select u_id, u_email, u_name,  u_my_ref_code, u_friend_ref_code, w_amount "
            + "from users, wallet "
            + "where u_id = w_u_id and u_email = ? and u_pwd = ?";

    public LoginDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public User login(String userId) {
        User user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(loginQuery);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("u_id"));
                user.setEmail(rs.getString("u_email"));
                user.setName(rs.getString("u_name"));
                user.setMyRefCode(rs.getString("u_my_ref_code"));
                user.setFriendRefCode(rs.getString("u_friend_ref_code"));
                user.setAmount(rs.getFloat("w_amount"));
                log.info("Amount In DAO : " + rs.getFloat("w_amount"));
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
                if (pstmt != null) {
                    pstmt.close();
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
        return user;
    }

    public User login(String email, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(loginEmailAndPwdQuery);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("u_id"));
                user.setEmail(rs.getString("u_email"));
                user.setName(rs.getString("u_name"));
                user.setMyRefCode(rs.getString("u_my_ref_code"));
                user.setFriendRefCode(rs.getString("u_friend_ref_code"));
                user.setAmount(rs.getFloat("w_amount"));
                log.info("Amount In DAO : " + rs.getFloat("w_amount"));
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
                if (pstmt != null) {
                    pstmt.close();
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
        return user;
    }

    public String getUserPassword(String email, String ip) {
        Connection connection = null;
        CallableStatement cstmt = null;
        String password = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call fp_forgot_password(?,?,?)}");
            cstmt.setString(1, email);
            cstmt.setString(2, ip);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);

            cstmt.execute();

            password = cstmt.getString(3);
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
        return password;
    }
}
