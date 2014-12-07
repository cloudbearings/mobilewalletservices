<%-- 
    Document   : resetPwd
    Created on : 7 Dec, 2014, 5:32:53 PM
    Author     : Gopi
--%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Wallet+ Android App offers free gift cards</title>
        <link href="/css/walletplus-landing.css" rel="stylesheet" type="text/css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    </head>

    <body>
        <div class="greenborder"></div>
        <div class="wrapper-inner">
            <div class="reset-block">
                <div class="logo-inner"><img src="/images/freeplus-logo.png" width="155" height="137" alt="wallet+ logo" /></div>
                <core:choose>
                    <core:when test="${rvalue eq 0}">
                        <div class="form-section">
                            <div class="form-title">Reset Password</div>
                            <core:if test="${pwdNotMatched ne null}" >
                                <span style="color: red; text-align: center; font-weight: bold; display: block;" >
                                    Passwords are not matched.
                                </span>
                            </core:if>
                            <form action="/changePassword/<%=id%>" method="post">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td><label for="password" >Enter new password</label></td>
                                    </tr>
                                    <tr>
                                        <td><input type="password" name="password" id="password" /></td>
                                    </tr>
                                    <tr>
                                        <td><label for="repassword" >Re-enter new password</label></td>
                                    </tr>
                                    <tr>
                                        <td><input type="password" name="retypepwd" id="repassword" /></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><input type="submit" name="button" id="button" value="Submit" class="buttongreen"/></td>
                                    </tr>
                                </table>
                            </form>
                        </div>        
                    </core:when>
                    <core:otherwise>
                        <div class="message-title">Link has been expired.</div>
                    </core:otherwise>
                </core:choose>

            </div>
        </div>
        <div class="operator-logos">
            <p>&copy; All Rights Reserved</p>
        </div>
    </body>
</html>