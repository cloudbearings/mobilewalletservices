/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.dao;

import com.mobilewallet.connection.ConnectionUtil;
import com.mobilewallet.users.dto.Balance;
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
public class BalanceDAO {

    private Log log = LogFactory.getLog(BalanceDAO.class);
    private static final String walletInfoQuery = "select w_amount, w_total_credits from wallet where w_u_id = ?";

    private DataSource dataSource;

    public BalanceDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public Balance balanceInfo(long userId) {
        Balance w = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(walletInfoQuery);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                w = new Balance();
                w.setAmount(rs.getFloat("w_amount"));
                w.setTotalAmount(rs.getFloat("w_total_credits"));
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {

            }
        }
        return w;
    }
}
