package com.mobilewallet.connection;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectionUtil {

    private static Log log = LogFactory.getLog(ConnectionUtil.class);
    private static String dataSourceJNDIName = "java:comp/env/jdbc/ds";
    private static String dataSource2JNDIName = "java:comp/env/jdbc/ds2";
    private static DataSource dataSource;
    private static DataSource dataSource2;
    static int i = 0;

    static {
        System.out.println("Datasource : " + dataSource);
        System.out.println("Loaded : " + i);
        i++;
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                log.info("In the ConnectionUtil:getDataSource():Befor calling lookup on context");
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup(dataSourceJNDIName);
                ctx.close();//try by making this line a comment to solve database frequent connectivity problem
            } catch (Exception e) {
                log.error("Can not create DataSource " + e.getMessage());
                throw new IllegalStateException(e.getMessage());
            }
        }
        return dataSource;
    }

    public static DataSource getDataSource2() {
        if (dataSource2 == null) {
            try {
                log.info("In the ConnectionUtil:getDataSource2():Befor calling lookup on context");
                Context ctx = new InitialContext();
                dataSource2 = (DataSource) ctx.lookup(dataSource2JNDIName);
                ctx.close();//try by making this line a comment to solve database frequent connectivity problem
            } catch (Exception e) {
                log.error("Can not create DataSource2 " + e.getMessage());
                throw new IllegalStateException(e.getMessage());
            }
        }
        return dataSource2;
    }

    public static void setDataSource(DataSource ds) {
        dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public static void beginTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    public static void endTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(true);
    }

    public static void commit(Connection conn) throws SQLException {
        conn.commit();
    }

    public static void rollback(Connection conn) throws SQLException {
        conn.rollback();
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {

                //if connection is already closed we are not going to close the connection which is already closed
                if (!conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException e) {
                log.error("SQL Exception caught while closing Connection :" + e.getMessage());

            }
        }
    }

    public static void close(Statement stm) {
        if (stm != null) {
            try {

                stm.close();

            } catch (SQLException e) {
                log.error("SQL Exception caught while closing Statement :" + e.getMessage());
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("SQL Exception caught while closing ResultSet :" + e.getMessage());
            }
        }
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("SQL Exception caught while closing ResultSet :" + e.getMessage());
            }
        }

        if (stmt != null) {
            try {

                stmt.close();

            } catch (SQLException e) {
                log.error("SQL Exception caught while closing Statement :" + e.getMessage());
            }
        }

        if (conn != null) {
            try {

                //if connection is already closed we are not going to close the connection which is already closed
                if (!conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException e) {
                log.error("SQL Exception caught while closing Connection :" + e.getMessage());

            }
        }
    }
}
