<%-- 
    Document   : logout
    Created on : Nov 29, 2013, 1:41:17 PM
    Author     : Gopi
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@include file="/jsp/common/idostiOutSideHeader.jsp" %>

<!--CONTENT STARTS HERE -->
<style type="text/css">
    .element
    {
        text-shadow: 0px 0px 7px rgba(0, 0, 0, 0.75);
        color: #0080ff;
        font-size: 45px;
    }
    .link {
        color: #eec10e;
        font-size: 30px;
        line-height: 18px;
        font-weight: bold;
    }
</style>
<div style="min-height: 420px;">
    <h6 align="center" class="element" style="margin: 30px;">${success}</h6>
    <div style="font-family:Arial, Helvetica, sans-serif; font-size:30px; font-weight:bold; color:#9e9e9e; margin-top:10px; text-align: center;">
        Click to :
        <a href="/admin" class="link">Log In</a>
    </div>
</div>

<!--CONTENT ENDS HERE --> 

</div>
<%@include file="/jsp/common/mobileWalletFooter.jsp" %>

</body>
</html>

