/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Gopi
 */
public class AdminAction extends org.apache.struts.action.Action {

    /* forward name="dsLogin" path="/jsp/common/loginPage.jsp" */
    private static final String DISPLAY_LOGINPAGE = "dsLogin";
    /* forward name="shAdminHome" path="/jsp/common/adminHome.jsp" */
    private static final String SHOW_ADMIN_HOME = "shAdminHome";
    private Log log = LogFactory.getLog(AdminAction.class);

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
        if (session == null || session.getAttribute("adminUser") == null) {
            return mapping.findForward(SHOW_ADMIN_HOME);
        }
        return mapping.findForward(DISPLAY_LOGINPAGE);
    }
}
