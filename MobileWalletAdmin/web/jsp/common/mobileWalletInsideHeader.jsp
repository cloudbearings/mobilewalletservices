<%-- 
    Document   : mobileWalletInsideHeader
    Created on : 27 Dec, 2014, 1:22:25 PM
    Author     : Gopi
--%>

<!--HEADER STARTS HERE -->
<div class="header">
    <div class="logo"><a href="/admin"><img src="images/mw_outside_logo.png" alt="iDosti logo" /></a></div>
    <div class="after-login">
        <div class="username">
            <!-- DROP DOWN MENU STARTS -->
            <div id="nav">
                <ul id="menu-mainnav" class="navbar">
                    <li><a href="#"><img src="images/user-nav-bar-pic.jpg" width="20" height="20"  style="margin-right:8px;"/>${adminUser.role} </a><span class="triangle"></span>
                        <ul class="sub-menu">
                            <li><a href="profile.do">My Profile</a></li>
                            <li><a href="account-settings.html">Account Settings</a></li>
                            <li><a href="logout.do">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- DROP DOWN MENU ENDS -->
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<!--HEADER ENDS HERE --> 
<!--SUBMENU STARTS HERE -->
<div>
    <div class="inner-top-nav">
        <div class="grid">
            <div id="myjquerymenu" class="jquerycssmenu">
                <ul>
                    <li><a href="admin.do?page=subitQuestion" id="HomePagePics">Submit Question</a>
                        <ul>
                            <li><a href="admin.do?page=viewQuestions" >Questions</a></li>
                            <li><a href="chat-history.html">Chat History</a></li>
                        </ul>
                    </li>
                    <li><a href="admin.do?page=profileUpdatedUsers" id="ImageUpdatedUsers">Questions</a></li>
                    <li><a href="admin.do?page=verifyData" id="VerifyData">Verify Data</a></li>
                </ul>
                <br style="clear: left" />
            </div>
        </div>
    </div>
</div>
<!--SUBMENU ENDS HERE --> 