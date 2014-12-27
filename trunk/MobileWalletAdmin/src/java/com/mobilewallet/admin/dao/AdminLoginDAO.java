/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.dao;

import com.mobilewallet.admin.dto.AdminUser;
import com.mobilewallet.connection.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gopi
 */
public class AdminLoginDAO {

    private final Log log = LogFactory.getLog(AdminLoginDAO.class);
    private final DataSource dataSource;
    private static final String loginQuery = "select ADM_USER_ID, ADM_EMAIL, ADM_ROLE from ADM_LOGIN_DETAILS where ADM_EMAIL=? and ADM_PWD=?";

    public AdminLoginDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public AdminUser login(String email, String password) {
        AdminUser user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(loginQuery);
            pstmt.setString(1, email.toLowerCase());
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new AdminUser();
                user.setAdminId(rs.getInt("ADM_USER_ID"));
                user.setEmail(rs.getString("ADM_EMAIL"));
                user.setRole(rs.getString("ADM_ROLE"));
            }
        } catch (Exception ex) {
            log.error("Unable To Login ", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            rs = null;
            pstmt = null;
            connection = null;
        }
        return user;
    }
}
