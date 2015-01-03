<%-- 
    Document   : adminHome
    Created on : 27 Dec, 2014, 11:37:01 AM
    Author     : Gopi
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Admin Home</title>
        <%@include file="/jsp/common/jsAndCss.jsp" %>
        <script type="text/javascript" src="js/jquery.form.js"></script>
        <script type="text/javascript" src="js/validateContactUs.js"></script>

        <script type="text/javascript">
            function ajaxCall() {
                alert("dfnjsdn");
                var url = "/questionsList?b=1";
                $.ajax({
                type: 'POST',
                        url: url,
                        beforeSend: function () {
                            // write form client side validations.
                            }
                    success: function(data) {
                    alert(data);
                    }
                });
            }
        </script>
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
            <div class="static-container" style="width:500px;">
                <div style="width:500px; float:left;">
                    <form action="/admin/submitQuestion.do" method="post">
                        <h3>Submit Question</h3>
                        <core:if test="${status ne null}">
                            <div id="successMsg" class="messages success" style="padding-top:5px; margin-bottom:5px; font-weight:bold; text-align:center;">
                                <p id="messageText"><core:out escapeXml="true" value="${status}"/></p>
                            </div>
                        </core:if>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="typequery">Type of question</label></td>
                                <td align="left" valign="middle">:</td>
                                <td align="left" valign="top"><select name="questionType" id="typequery" class="textfield" style="width:212px;" >
                                        <option value="-1">-------------- Select -------------</option>
                                        <option>Arithmetic</option>
                                        <option>Reasoning</option>
                                        <option>General Knowledge</option>
                                        <option>Vocabulary</option>
                                        <option>English</option>
                                        <option>Others</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="typequeryRequired" style="display:none;">Select type of question.</p>                                   
                                    <logic:messagesPresent property="typequery_error">
                                        <p class="inline-error"><html:errors property="typequery_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="top"><label for="question">Question</label></td>
                                <td align="left" valign="top">:</td>
                                <td align="left" valign="top"><textarea name="question" cols="32" rows="5" class="textarea" id="question" style="resize: none;"></textarea>

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="questionRequired" style="display:none;">Enter question.</p>                                   
                                    <logic:messagesPresent property="question_error">
                                        <p class="inline-error"><html:errors property="question_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="optiona">Option A</label></td>
                                <td width="10" align="left" valign="middle">:</td>
                                <td align="left" valign="top">
                                    <core:choose>
                                        <core:when test="${idostiUser ne null}">
                                            <input name="optiona" type="text" class="textfield" id="optiona" value="${idostiUser.email}"/>
                                        </core:when>
                                        <core:otherwise>
                                            <input name="optiona" type="text" class="textfield" id="optiona" />
                                        </core:otherwise>
                                    </core:choose>

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="optionaRequired" style="display:none;">Enter Option A value</p>                                   
                                    <logic:messagesPresent property="optiona_error">
                                        <p class="inline-error"><html:errors property="optiona_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="optionb">Option B</label></td>
                                <td align="left" valign="middle">:</td>
                                <td align="left" valign="top">
                                    <core:choose>
                                        <core:when test="${idostiUser ne null}">
                                            <input name="optionb" type="text" class="textfield" id="optionb" value="${idostiUser.optionbNumber}" />
                                        </core:when>
                                        <core:otherwise>
                                            <input name="optionb" type="text" class="textfield" id="optionb" />
                                        </core:otherwise>
                                    </core:choose>

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="optionbRequired" style="display: none">Enter Option B value.</p>
                                    <logic:messagesPresent property="optionb_error">
                                        <p class="inline-error"><html:errors property="optionb_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>

                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="optionc">Option C</label></td>
                                <td align="left" valign="middle">:</td>
                                <td align="left" valign="top"><input name="optionc" type="text" class="textfield" id="optionc" />

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="optioncRequired" style="display: none">Enter Option C value.</p>
                                    <logic:messagesPresent property="optionc_error">
                                        <p class="inline-error"><html:errors property="optionc_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="optiond">Option D</label></td>
                                <td align="left" valign="middle">:</td>
                                <td align="left" valign="top"><input name="optiond" type="text" class="textfield" id="optiond" />

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="optiondRequired" style="display: none">Enter Option D value.</p>
                                    <logic:messagesPresent property="optiond_error">
                                        <p class="inline-error"><html:errors property="optiond_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"><label for="answer">Answer</label></td>
                                <td align="left" valign="middle">:</td>
                                <td align="left" valign="top"><input name="answer" type="text" class="textfield" id="answer" />

                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="answerRequired" style="display: none">Enter answer value.</p>
                                    <logic:messagesPresent property="answer_error">
                                        <p class="inline-error"><html:errors property="answer_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left" valign="top"><label for="explanation">Explanation</label></td>
                                <td align="left" valign="top">:</td>
                                <td align="left" valign="top"><textarea name="explanation" cols="32" rows="5" class="textarea" id="explanation" style="resize: none;"></textarea>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td>
                                    <p class="inline-error" id="explanationRequired" style="display: none">Enter question explanation.</p>
                                    <logic:messagesPresent property="explanation_error">
                                        <p class="inline-error"><html:errors property="explanation_error"/></p>
                                    </logic:messagesPresent>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="left" valign="middle" height="8px"></td>
                            </tr>
                            <tr>
                                <td align="left">&nbsp;</td>
                                <td align="left">&nbsp;</td>
                                <td align="left">
                                    <core:if test="${idostiUser ne null}">
                                        <input name="page" type="hidden" value="loginUser"/>
                                    </core:if>
                                    <input type="submit" name="button" id="submitQuestionButton" value="Submit" class="green-button medium-button" /></td>
                            </tr>
                        </table>
                        <h5 class="lightgreen">&nbsp;</h5>
                        <core:if test="${reportOnfriendId ne null}">
                            <input type="hidden" name="rFriendId" value="${reportOnfriendId}"/>
                        </core:if>
                    </form>
                </div>

                <div class="clearfix"></div>
            </div>
            <!-- STATIC PAGE CONTAINER ENDS HERE --> 

        </div>
        <div class="grid">
            <div class="content-container" id="np">

            </div>
        </div>

    </body>
</html>
