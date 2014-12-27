<%-- 
    Document   : jsAndCss
    Created on : Nov 29, 2013, 5:34:37 PM
    Author     : Gopi
--%>

<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/purple-theme.css" rel="stylesheet" type="text/css" />
<link href="css/jquerycssmenu.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'/>
<!--[if lt IE 9]>
                <script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!--[if IE 7]>
                <link rel="stylesheet" type="text/css" href="css/ie7.css">
        <![endif]-->
<!--[if IE 8]>
                <link rel="stylesheet" type="text/css" href="css/ie8.css">
        <![endif]-->
<!--[if IE 9]>
                <link rel="stylesheet" type="text/css" href="css/ie9.css">
        <![endif]-->

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="js/jquery.dropdown.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".sub-menu").hide();
        $("ul.navbar li:has(ul)").click(function() {
            $("ul.sub-menu").slideDown('slow');
        });
        $(".sub-menu").mouseleave(function() {
            $(".sub-menu").slideUp('slow').stop;

        });

    });

</script>