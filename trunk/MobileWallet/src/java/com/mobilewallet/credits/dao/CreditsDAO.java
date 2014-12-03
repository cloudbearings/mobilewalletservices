/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.credits.dao;

import com.mobilewallet.connection.ConnectionUtil;
import com.mobilewallet.credits.dto.RechargeHistoryDTO;
import com.mobilewallet.credits.dto.WalletHistoryDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gopi
 */
public class CreditsDAO {

    private static final String creditCountQuery = "select count(*) from wp_user_credits where wuc_user_id = ?";
    private static final String debitCountQuery = "select count(*) from wp_user_debits where wud_user_id = ?";

    private static final String creditHistorQuery = ""
            + " select * from ( "
            + " select wuc_id, to_char(wuc_time,'dd-Mon-yy') d, wuc_desc, wuc_amount, row_number() over(order by wuc_time desc) roTime "
            + " from wp_user_credits where wuc_user_id = ? and wuc_amount != 0 "
            + " ) where roTime between ? and ?";

    private static final String debitHistorQuery = ""
            + " select * from ( "
            + " select wud_id, to_char(wud_time,'dd-Mon-yy') d, wud_amount, wud_desc, wud_status, wud_code, row_number() over(order by wud_time desc) roTime "
            + " from wp_user_debits where wud_user_id = ?"
            + " ) where roTime between ? and ?";

    private Log log = LogFactory.getLog(CreditsDAO.class);
    private DataSource dataSource;

    public CreditsDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public int updateCredits(long userId, String isCorrect, int position) {
        int updated = 0;
        Connection connection = null;
        CallableStatement cstmt = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call update_credits_proc(?,?,?,?)}");
            cstmt.setLong(1, userId);
            cstmt.setString(2, isCorrect);
            cstmt.setInt(3, position);
            cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            cstmt.execute();

            updated = cstmt.getInt(4);
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
        return updated;
    }

    public int creditCount(long userId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(creditCountQuery);
            cstmt.setLong(1, userId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public List<WalletHistoryDTO> creditHistory(long userId, int begin, int end) {
        List<WalletHistoryDTO> list = new ArrayList<WalletHistoryDTO>();

        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(creditHistorQuery);
            cstmt.setLong(1, userId);
            cstmt.setInt(2, begin);
            cstmt.setInt(3, end);
            rs = cstmt.executeQuery();
            WalletHistoryDTO ch = null;
            while (rs.next()) {
                ch = new WalletHistoryDTO();
                ch.setCid(rs.getLong("wuc_id"));
                ch.setDesc(rs.getString("wuc_desc"));
                ch.setAmount(rs.getFloat("wuc_amount"));
                ch.setcTime(rs.getString("d"));
                list.add(ch);
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

        return list;
    }

    public int debitCount(long userId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(debitCountQuery);
            cstmt.setLong(1, userId);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
        return count;
    }

    public List<RechargeHistoryDTO> debitHistory(long userId, int begin, int end) {
        List<RechargeHistoryDTO> list = new ArrayList<RechargeHistoryDTO>();

        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(debitHistorQuery);
            cstmt.setLong(1, userId);
            cstmt.setInt(2, begin);
            cstmt.setInt(3, end);
            rs = cstmt.executeQuery();
            RechargeHistoryDTO dht = null;
            while (rs.next()) {
                dht = new RechargeHistoryDTO();
                dht.setdTime(rs.getString("d"));
                dht.setAmount(rs.getFloat("wud_amount"));
                dht.setDesc(rs.getString("wud_desc"));
                dht.setCode(rs.getString("wud_code"));
                dht.setDid(rs.getLong("wud_id"));
                dht.setStatus(rs.getString("wud_status"));

                list.add(dht);
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

        return list;
    }
}
