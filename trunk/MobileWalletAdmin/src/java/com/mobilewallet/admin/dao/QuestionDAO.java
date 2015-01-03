/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.dao;

import com.mobilewallet.admin.dto.QuestionDTO;
import com.mobilewallet.admin.util.DBAdapter;
import com.mobilewallet.connection.ConnectionUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gopi
 */
public class QuestionDAO {

    private Log log = LogFactory.getLog(QuestionDAO.class);

    private DataSource dataSource;

    private static final String getUserQuestiosCountQuery = "select count(*) from " + DBAdapter.QUESTION_TABLE_NAME;
    private static final String getQuestiosQuery = "select * from (select " + DBAdapter.QUESTION + ", " + DBAdapter.OPTION_1 + ", " + DBAdapter.OPTION_2 + ", " + DBAdapter.OPTION_3 + ", " + DBAdapter.OPTION_4 + ", " + DBAdapter.ANSWER + ", " + DBAdapter.EXPLANATION + ", row_number() over(order by " + DBAdapter.TIME + " desc) roTime"
            + " from " + DBAdapter.QUESTION_TABLE_NAME + ") where roTime between ? and ?";

    public QuestionDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public int submitQuestion(String question, String qType, String option1, String option2, String option3, String option4, String answer,
            String explanation, String isAdminApproved) {
        Connection connection = null;
        CallableStatement pstmt = null;
        ResultSet rs = null;
        int submitted = 0;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareCall("{call SUBMIT_QUESTION(?,?,?,?,?,?,?,?,?,?)}");
            pstmt.setString(1, qType);
            pstmt.setString(2, question);
            pstmt.setString(3, option1);
            pstmt.setString(4, option2);
            pstmt.setString(5, option3);
            pstmt.setString(6, option4);
            pstmt.setString(7, answer);
            pstmt.setString(8, explanation);
            pstmt.setString(9, isAdminApproved);
            pstmt.registerOutParameter(10, java.sql.Types.INTEGER);
            pstmt.execute();
            submitted = pstmt.getInt(10);
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
        return submitted;
    }

    public int getQuestionsCount() {
        int count = 0;
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(getUserQuestiosCountQuery);
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

    public ArrayList<QuestionDTO> getQuestions(int begin, int end) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<QuestionDTO> questionsList = new ArrayList<QuestionDTO>();
        QuestionDTO question = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(getQuestiosQuery);
            pstmt.setInt(1, begin);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                question = new QuestionDTO();
                question.setQuestion(rs.getString(DBAdapter.QUESTION));
                question.setOption1(rs.getString(DBAdapter.OPTION_1));
                question.setOption2(rs.getString(DBAdapter.OPTION_2));
                question.setOption3(rs.getString(DBAdapter.OPTION_3));
                question.setOption4(rs.getString(DBAdapter.OPTION_4));
                question.setAnswer(rs.getString(DBAdapter.ANSWER));
                question.setExplanation(rs.getString(DBAdapter.EXPLANATION));
                questionsList.add(question);
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
        return questionsList;
    }

    public int approveQuestion(long userId, long q_id, String is_admin_approved) {
        Connection connection = null;
        CallableStatement pstmt = null;
        ResultSet rs = null;
        int approved = 0;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareCall("{call approve_question(?,?,?,?)}");
            pstmt.setLong(1, userId);
            pstmt.setLong(2, q_id);
            pstmt.setString(3, is_admin_approved);

            pstmt.registerOutParameter(4, java.sql.Types.INTEGER);
            pstmt.execute();
            approved = pstmt.getInt(4);
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
        return approved;
    }

}
