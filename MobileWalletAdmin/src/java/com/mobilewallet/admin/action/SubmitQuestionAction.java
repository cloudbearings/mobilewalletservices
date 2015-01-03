/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.action;

import com.mobilewallet.admin.bo.QuestionBO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Gopi
 */
public class SubmitQuestionAction extends org.apache.struts.action.Action {

    /* forward name="success" path="/jsp/submitQuestionResponse.jsp" */
    private static final String SUCCESS = "success";
    private Log log = LogFactory.getLog(SubmitQuestionAction.class);

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
        int submitted = QuestionBO.submitQuestion(request.getParameter("question"), request.getParameter("questionType"), request.getParameter("optiona"), request.getParameter("optionb"),
                request.getParameter("optionc"), request.getParameter("optiond"), request.getParameter("answer"), request.getParameter("explanation"),
                "Y");
        String message;
        if (submitted == 0) {
            message = "Your question has been submitted successfully.";
        } else {
            message = "Internal server error";
        }
        request.setAttribute("status", message);
        return mapping.findForward(SUCCESS);
    }
}
