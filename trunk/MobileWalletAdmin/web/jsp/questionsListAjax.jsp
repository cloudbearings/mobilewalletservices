<%-- 
    Document   : questionsListAjax
    Created on : 3 Jan, 2015, 12:55:26 PM
    Author     : Gopi
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Questions</title>
        <%@include file="/jsp/common/jsAndCss.jsp" %>
    </head>

    <body class="registration">
        <div class="grid"> 
            <%@include file="/jsp/common/mobileWalletInsideHeader.jsp" %>
            <%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

            <style type="text/css">
                .static-container {
                    color:#414141;
                    line-height:18px;
                }
                .static-container p {
                    margin-top:5px;
                    color:#414141;
                }
                .static-container ul li {
                    list-style-type:disc;
                    margin-left:25px;
                }
                p.inline-error {
                    font-family: Arial, Helvetica, sans-serif;
                    font-size: 12px;
                    font-weight: bold;
                    color: #F00;
                    margin-bottom:0px;
                }
            </style>
            <!-- STATIC PAGE CONTAINER STARTS HERE -->
            <div class="static-container" style="width:700px;">
                <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
                <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <core:choose>
                    <core:when test="${questionsDetails ne null}">
                        <core:forEach var="question" items="${questionsDetails}">
                            <!-- FIRST PROFILE STARTS HERE -->
                            <div class="search-profile-container">
                                <a href="">     <div class="profilepic">

                                    </div>    </a>
                                <div class="profile-details">
                                    <h3 class="purple"><a href="">Q: <core:out escapeXml="true" value="${question.question}"/></a></h3>
                                    <p class="summary">A) <core:out escapeXml="true" value="${question.option1}"/><br/>B) <core:out escapeXml="true" value="${question.option2}"/>
                                        <br/>C) <core:out escapeXml="true" value="${question.option3}"/><br/>D) <core:out escapeXml="true" value="${question.option4}"/></p>
                                    <ul class="profile-list">
                                        <li class="view-profile">Answer: <b><core:out escapeXml="true" value="${question.answer}"/></b></li>
                                        <!--<li class="remove-friend"><a onclick="" style="cursor: pointer;">Remove Friend</a></li>-->
                                    </ul>
                                </div>

                                <div class="clearfix"></div>
                            </div>
                            <!-- FIRST PROFILE ENDS HERE --> 
                        </core:forEach>
                    </core:when>
                </core:choose>
                <div id="pagination">
                    <core:choose>
                        <core:when test="${count % 12 eq 0 }">
                            <fmt:formatNumber var="totalNoOfpages" value="${ count/12 }" pattern="0" />
                        </core:when>
                        <core:otherwise>
                            <fmt:formatNumber var="totalNoOfpages" value="${ (count/12.0)+0.5 }" pattern="0" />
                        </core:otherwise>
                    </core:choose>
                    <fmt:formatNumber var="page" value="${ (start/12.0)+0.5 }" pattern="0" />
                    <!-- PAGINATION STARTS -->
                    <core:if test="${totalNoOfpages ge 2}">
                        <div class="pagination">
                            <core:if test="${page le totalNoOfpages-1}">
                                <a class="page gradient" id="showMore" style="cursor: pointer;" onclick="return pagination('${ (page*12)+1 }');">show more >></a>
                                <img src="/images/ajax-loader.gif" style="margin-left: 5px; display: none; " id="sending" />
                            </core:if>
                        </div>
                    </core:if>
                </div>
                <!-- PAGINATION ENDS -->
            </div>
            <!-- STATIC PAGE CONTAINER ENDS HERE --> 

        </div>
        <div class="grid">
            <div class="content-container" id="np">

            </div>
        </div>

    </body>
</html>