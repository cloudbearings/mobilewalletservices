<%-- 
    Document   : changePwd
    Created on : 7 Dec, 2014, 5:34:43 PM
    Author     : Gopi
--%>

<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Free+ Android App offers Free Mobile Recharge</title>
        <link href="/css/walletplus-landing.css" rel="stylesheet" type="text/css" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    </head>

    <body>
        <div class="greenborder"></div>
        <div class="wrapper-inner">
            <div class="reset-block">
                <div class="logo-inner"><img src="/images/freeplus-logo.png" width="97" height="97" alt="Free+ logo" /></div>
                <core:choose>
                    <core:when test="${rvalue eq 0}">
                        <div class="message-title">Your password has been changed successfully</div>
                    </core:when>
                    <core:otherwise>
                        <div class="message-title">Failed to change password. Try again.</div>
                    </core:otherwise>
                </core:choose>
            </div>
        </div>

    </body>
</html>
