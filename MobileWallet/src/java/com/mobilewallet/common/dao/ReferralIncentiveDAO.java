/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.common.dao;

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
public class ReferralIncentiveDAO {

    private Log log = LogFactory.getLog(ReferralIncentiveDAO.class);
    private DataSource dataSource;

    private static final String getRAmountQuery = "select * from referral_amount";

    public ReferralIncentiveDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public Object[] addReferralIncetive(long userId, String refCode, String imei, String ip) {
        int added = 0;
        Connection connection = null;
        CallableStatement cstmt = null;
        String gcmId = null;
        Object[] obj = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call ADD_REFERRAL_CREDIT(?,?,?,?,?,?,?)}");
            cstmt.setLong(1, userId);
            cstmt.setString(2, refCode);
            cstmt.setString(3, imei);
            cstmt.setString(4, ip);
            cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
            cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);

            cstmt.execute();

            added = cstmt.getInt(5);
            gcmId = cstmt.getString(6);
            obj = new Object[3];
            obj[0] = added;
            obj[1] = gcmId;
            obj[2] = cstmt.getString(7);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error in add referral incentive dao : " + ex.getMessage() + ", USER ID " + userId + ", refCode : " + refCode + ", imei : " + imei + ", IP : " + ip);
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

    public String showInvitationStrig(long userId) {
        Connection connection = null;
        CallableStatement cstmt = null;
        String show = null;

        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call wp_show_invitation(?,?)}");
            cstmt.setLong(1, userId);
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            cstmt.execute();

            show = cstmt.getString(2);

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

        return show;
    }

    public String[] getRAmount() {
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        String[] amount = null;

        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(getRAmountQuery);
            rs = cstmt.executeQuery();
            if (rs != null && rs.next()) {
                amount = new String[3];
                amount[0] = rs.getString("rm");
                amount[1] = rs.getString("erm");
                amount[2] = rs.getString("daily_credits");
            }
            log.info("Amount : " + amount);
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

        return amount;
    }
}
