/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.dao;

import com.mobilewallet.connection.ConnectionUtil;
import com.mobilewallet.users.dto.User;
import com.mobilewallet.users.dto.UserProfile;
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
public class UserDAO {

    private Log log = LogFactory.getLog(UserDAO.class);
    private DataSource dataSource;

    private static final String getUserInfoQuery = "select u_id,u_email,u_name ,u_gender,to_char(u_dob,'dd-Mon-yyyy') dob, u_mobile_code, u_mobile_number, u_occupation, u_income, u_fb_id "
            + " from users where u_id = ? ";
    private static final String getGCMIDQuery = ""
            + " select u_gcm_id "
            + " from users where u_id = ? ";

    public UserDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public User userInfo(long userId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(getUserInfoQuery);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("u_id"));
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

    public UserProfile userProfile(long userId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserProfile user = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(getUserInfoQuery);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new UserProfile();
                user.setName(rs.getString("u_name"));
                user.setDob(rs.getString("dob"));
                user.setMcode(rs.getString("u_mobile_code"));
                user.setMnumber(rs.getString("u_mobile_number"));
                user.setOccupation(rs.getString("u_occupation"));
                user.setIncome(rs.getString("u_income"));
                user.setGender(rs.getString("u_gender"));
                user.setFbId(rs.getString("u_fb_id"));
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

    public int updateProfile(long userId, String mCode, String mobileNumber, String dob, String gender, String occupation, String income) {
        Connection connection = null;
        CallableStatement pstmt = null;
        ResultSet rs = null;
        int updated = 0;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareCall("{call UPDATE_PROFILE(?,?,?,?,?,?,?,?)}");
            pstmt.setLong(1, userId);
            pstmt.setString(2, mCode);
            pstmt.setString(3, mobileNumber);
            pstmt.setString(4, dob);
            pstmt.setString(5, gender);
            pstmt.setString(6, occupation);
            pstmt.setString(7, income);
            pstmt.registerOutParameter(8, java.sql.Types.INTEGER);
            pstmt.execute();
            updated = pstmt.getInt(8);
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
        return updated;
    }

    public String getGCMID(long userId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String gcmId = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(getGCMIDQuery);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                gcmId = rs.getString("u_gcm_id");
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
        return gcmId;
    }
}
