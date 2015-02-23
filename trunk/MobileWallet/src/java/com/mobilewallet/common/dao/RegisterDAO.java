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
 * @author Gopi
 */
public class RegisterDAO {

    Log log = LogFactory.getLog(RegisterDAO.class);
    private DataSource ds = null;

    public RegisterDAO() {
        ds = ConnectionUtil.getDataSource();
    }

    public Object[] registerUser(String email, String fname, String lname, String dob, String gender, String pwd, String imei, String accounts, String country, String handsetModel, String androidVer, String emulator, String gcmId, String androidId, String refCode, String ip, String fbId) {
        Object[] obj = null;
        Connection con = null;
        CallableStatement cstmt = null;
        try {
            con = ds.getConnection();
            cstmt = con.prepareCall("{call REGISTER_USER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, email);
            cstmt.setString(2, fname);
            cstmt.setString(3, lname);
            cstmt.setString(4, dob);
            cstmt.setString(5, gender);
            cstmt.setString(6, pwd);
            cstmt.setString(7, imei);
            cstmt.setString(8, accounts);
            cstmt.setString(9, country);
            cstmt.setString(10, handsetModel);
            cstmt.setString(11, androidVer);
            cstmt.setString(12, emulator);
            cstmt.setString(13, gcmId);
            cstmt.setString(14, androidId);
            cstmt.setString(15, refCode);
            cstmt.setString(16, ip);
            cstmt.setString(17, fbId);
            cstmt.registerOutParameter(18, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(19, java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(20, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(21, java.sql.Types.INTEGER);

            cstmt.execute();

            obj = new Object[4];
            obj[0] = cstmt.getInt(18);//rvalue
            obj[1] = cstmt.getString(19);//user ref code
            obj[2] = cstmt.getFloat(20);//balance
            obj[3] = cstmt.getLong(21);//user id
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {

            }
        }
        return obj;
    }

    public void updateGCM(String userId, String gcmId) {
        Connection con = null;
        CallableStatement cstmt = null;

        try {
            con = ds.getConnection();
            cstmt = con.prepareCall("{call wp_update_gcm(?,?,?)}");
            cstmt.setString(1, userId);
            cstmt.setString(2, gcmId);
            cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            cstmt.execute();

        } catch (Exception ex) {

        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
            } catch (Exception ex) {

            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {

            }
        }
    }
}
