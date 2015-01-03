/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.bo;

import com.mobilewallet.admin.dao.QuestionDAO;
import com.mobilewallet.admin.dto.QuestionDTO;
import java.util.ArrayList;

/**
 *
 * @author Gopi
 */
public class QuestionBO {

    private static final QuestionDAO QUESTION_DAO = new QuestionDAO();

    public static int submitQuestion(String question, String qType, String option1, String option2, String option3, String option4, String answer,
            String explanation, String isAdminApproved) {
        return QUESTION_DAO.submitQuestion(question, qType, option1, option2, option3, option4, answer, explanation, isAdminApproved);
    }

    public static int getQuestionsCount() {
        return QUESTION_DAO.getQuestionsCount();
    }

    public static ArrayList<QuestionDTO> getQuestions(int begin, int end) {
        return QUESTION_DAO.getQuestions(begin, end);
    }
}
