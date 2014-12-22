/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.dao;

import com.mobilewallet.connection.ConnectionUtil;
import com.mobilewallet.users.dto.UserQuestionsDTO;
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
public class UserQuestionsDAO {

    private Log log = LogFactory.getLog(UserQuestionsDAO.class);

    private DataSource dataSource;

    private static final String getUserQuestiosCountQuery = "select count(*) from user_questions where uq_user_id = ? and status=Y";
    private static final String getUserQuestiosQuery = "select * from (select uq_question, uq_answerA, uq_answerB, uq_answerC, uq_answerD, answer, status, row_number() over(order by uq_time desc) roTime"
            + " from user_questions where uq_user_id = ? and status=Y) where roTime between ? and ?";

    public UserQuestionsDAO() {
        dataSource = ConnectionUtil.getDataSource();
    }

    public int submitQuestion(long userId, String question, String answerA, String answerB, String answerC, String answerD, String answer) {
        Connection connection = null;
        CallableStatement pstmt = null;
        ResultSet rs = null;
        int submitted = 0;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareCall("{call submit_question(?,?,?,?,?,?,?,?)}");
            pstmt.setLong(1, userId);
            pstmt.setString(2, question);
            pstmt.setString(3, answerA);
            pstmt.setString(4, answerB);
            pstmt.setString(5, answerC);
            pstmt.setString(6, answerD);
            pstmt.setString(7, answer);
            pstmt.registerOutParameter(8, java.sql.Types.INTEGER);
            pstmt.execute();
            submitted = pstmt.getInt(8);
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

    public int userQuestionsCount(long userId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement cstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            cstmt = connection.prepareStatement(getUserQuestiosCountQuery);
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

    public List<UserQuestionsDTO> userQuestions(long userId, int begin, int end) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserQuestionsDTO> userQuestionsList = new ArrayList<UserQuestionsDTO>();
        UserQuestionsDTO userQuestions = null;
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(getUserQuestiosQuery);
            pstmt.setLong(1, userId);
            pstmt.setInt(2, begin);
            pstmt.setInt(3, end);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userQuestions = new UserQuestionsDTO();
                userQuestions.setQuestion(rs.getString("uq_question"));
                userQuestions.setAnswerA(rs.getString("uq_answerA"));
                userQuestions.setAnswerB(rs.getString("uq_answerB"));
                userQuestions.setAnswerC(rs.getString("uq_answerC"));
                userQuestions.setAnswerD(rs.getString("uq_answerD"));
                userQuestions.setAnswer(rs.getString("uq_answer"));
                userQuestions.setAnswer(rs.getString("status"));
                userQuestionsList.add(userQuestions);
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
        return userQuestionsList;
    }
}
