<%-- 
    Document   : loginPage
    Created on : 27 Dec, 2014, 11:20:14 AM
    Author     : Gopi
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@include file="/jsp/common/mobileWalletOutsideHeader.jsp" %>

<!--CONTENT STARTS HERE -->
<div class="registration-container">
    <div class="greybox-small">
        <div class="email-signin">
            <h1 class="green">Login</h1>
            <core:if test="${invalidLogin ne null}">
                <div class="messages error" style="padding-top:5px; margin-bottom:5px; font-weight:bold; text-align:center;">
                    <p>Incorrect Email / Password Combination</p>
                </div>
            </core:if>
            <form action="loginPage.do" method="post">
                <label for="login-email" >Email</label>
                <input type="text" name="email" id="login-email" class="textfield" />
                <p class="inline-error" style="display: none" id="emailRequired">Enter your email address.</p>
                <p class="inline-error" style="display: none" id="invalid_email">Invalid email address.</p>
                <p class="tmar10">
                    <label for="login-password" >Password</label>
                </p>
                <input type="password" name="pwd" id="login-password" class="textfield" />
                <p class="inline-error" style="display: none" id="passwordRequired">Enter your password.</p>
                <div class="login-preferences">
                    <div class="clearfix"></div>
                </div>
                <p>
                    <input type="submit" name="button" id="login" value="Login" class="green-button medium-button" />
                </p>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<!--CONTENT ENDS HERE --> 

</div>
</body>
</html>
