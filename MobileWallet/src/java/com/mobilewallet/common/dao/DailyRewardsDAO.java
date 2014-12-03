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

/**
 *
 * @author Gopi
 */
public class DailyRewardsDAO {

    private DataSource ds;

    public DailyRewardsDAO() {
        ds = ConnectionUtil.getDataSource();
    }

    public int addDailyRewards(long userId, String ip, String imei) {
        Connection con = null;
        CallableStatement cstmt = null;
        int rvalue = -1;
        try {
            con = ds.getConnection();
            cstmt = con.prepareCall("{call wp_daily_reward(?,?,?,?)}");
            cstmt.setLong(1, userId);
            cstmt.setString(2, ip);
            cstmt.setString(3, imei);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.execute();
            rvalue = cstmt.getInt(4);

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
        return rvalue;
    }
}
