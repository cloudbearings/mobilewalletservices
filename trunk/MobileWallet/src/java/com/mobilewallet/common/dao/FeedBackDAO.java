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
public class FeedBackDAO {

    private Log log = LogFactory.getLog(FeedBackDAO.class);

    private DataSource dataSource;

    public FeedBackDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public void feedBack(long userId, String feedType, String feedText, String ip, String email) {
        Connection connection = null;
        CallableStatement cstmt = null;

        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareCall("{call FEEDBACK_PROC(?,?,?,?,?)}");
            cstmt.setLong(1, userId);
            cstmt.setString(2, feedType);
            cstmt.setString(3, feedText);
            cstmt.setString(4, email);
            cstmt.setString(5, ip);
            cstmt.execute();

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
    }
}
