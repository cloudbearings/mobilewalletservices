/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.admin.action;

import com.mobilewallet.admin.bo.AdminLoginBO;
import com.mobilewallet.admin.dto.AdminUser;
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
public class AdminLoginAction extends org.apache.struts.action.Action {

    /* forward name="dsAdminHome" path="/jsp/common/adminHome.jsp" */
    private static final String DISPLAY_ADMIN_HOME = "dsAdminHome";

    /* forward name="failure" path="/jsp/common/dsLogin.jsp" */
    private static final String FAILURE = "failure";

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
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        if (email != null && pwd != null) {
            AdminUser adminUser = AdminLoginBO.login(email, pwd);
            if (adminUser != null) {
                session.setAttribute("adminUser", adminUser);
                return mapping.findForward(DISPLAY_ADMIN_HOME);
            }
        }
        request.setAttribute("invalidLogin", "Invalid email or password");
        return mapping.findForward(FAILURE);
    }
}
