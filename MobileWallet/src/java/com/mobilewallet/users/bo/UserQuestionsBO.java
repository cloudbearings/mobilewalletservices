/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.users.bo;

import com.mobilewallet.users.dao.UserQuestionsDAO;
import com.mobilewallet.users.dto.UserQuestionsDTO;
import java.util.List;

/**
 *
 * @author Gopi
 */
public class UserQuestionsBO {

    private static final UserQuestionsDAO USER_QUESTIONS_DAO = new UserQuestionsDAO();

    public static int submitQuestion(long userId, String question, String answerA, String answerB, String answerC, String answerD, String answer) {
        return USER_QUESTIONS_DAO.submitQuestion(userId, question, answerA, answerB, answerC, answerD, answer);
    }

    public static int userQuestionsCount(long userId) {
        return USER_QUESTIONS_DAO.userQuestionsCount(userId);
    }

    public static List<UserQuestionsDTO> userQuestions(long userId, int begin, int end) {
        return USER_QUESTIONS_DAO.userQuestions(userId, begin, end);
    }
}
