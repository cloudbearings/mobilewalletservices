/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.action;

import com.mobilewallet.admin.bo.QuestionBO;
import com.mobilewallet.admin.dto.AdminUser;
import com.mobilewallet.admin.dto.QuestionDTO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Gopi
 */
public class QuestionsListAction extends org.apache.struts.action.Action {

    /* forward name="showQuestionsList" path="/jsp/questionsListAjax.jsp" */
    private static final String SHOW_QUESTIONS_LIST = "showQuestionsList";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(false);
        AdminUser user = (AdminUser) session.getAttribute("adminUser");
        if (user != null) {

            int start = 1;
            if (request.getParameter("b") != null) {
                try {
                    start = Integer.parseInt(request.getParameter("b"));
                } catch (Exception ex) {
                }
            }

            int count = QuestionBO.getQuestionsCount();
            request.setAttribute("count", count);
            if (count > 0) {
                if (start == 0) {
                    start = 1;
                }

                if ((start % 12 != 1) || (start > count)) {
                    start = 1;
                }

                int end = start + 11;
                if (end > count) {
                    end = count;
                }

                ArrayList<QuestionDTO> questionsDetails = QuestionBO.getQuestions(start, end);
                if (questionsDetails != null) {
                    request.setAttribute("questionsDetails", questionsDetails);
                    request.setAttribute("start", start);
                    request.setAttribute("end", end);

                    //No.of pages
                    if (count % 12 == 0) {
                        request.setAttribute("totalNoOfPages", Math.round(count / 12));
                    } else {
                        request.setAttribute("totalNoOfPages", Math.round((count / 12.0) + 0.5));
                    }

                    request.setAttribute("page", Math.round((start / 12.0) + 0.5));
                }
                if (start > 12) {
                    return mapping.findForward("shMoreResults");
                }
            }
        }
        return mapping.findForward(SHOW_QUESTIONS_LIST);
    }
}
