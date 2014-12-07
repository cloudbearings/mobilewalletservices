/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilewallet.actions;

import com.mobilewallet.common.bo.ForgotPasswordBO;
import com.mobilewallet.config.Config;
import com.mobilewallet.util.MobileWalletDE;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Gopi
 */
public class ChangePasswordAction extends org.apache.struts.action.Action {

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
        String id = request.getParameter("id");
        String pwd = request.getParameter("password");
        String rtPwd = request.getParameter("retypepwd");
        String salt = Config.PWD_SALT + pwd;

        if (pwd == null || rtPwd == null || "".equals(pwd.trim()) || "".equals(rtPwd.trim())) {
            request.setAttribute("id", id);
            return mapping.findForward("resetPassword");
        } else if (!pwd.equals(rtPwd.trim())) {
            request.setAttribute("id", id);
            request.setAttribute("pwdNotMatched", "");
            return mapping.findForward("resetPassword");
        } else {

            MobileWalletDE f = new MobileWalletDE(Config.RESET_PWD_KEY);

            String uuid = null;
            String userId = null;
            try {
                String did = f.decryptURLSafe(id);
                uuid = did.split("\\$ #")[0];
                userId = did.split("\\$ #")[1];
            } catch (Exception ex) {

            }
            if (uuid != null && userId != null) {
                String md5PWD = org.apache.commons.codec.digest.DigestUtils.md5Hex(pwd + salt);
                int rvalue = ForgotPasswordBO.resetPassword(uuid, userId, md5PWD, request.getRemoteAddr());
                request.setAttribute("rvalue", rvalue);
            }
        }
        return mapping.findForward("changePassword");
    }
}
