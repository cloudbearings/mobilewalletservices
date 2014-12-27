/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#login").click(function() {
        var email = $('#login-email').val();
        var password = $('#login-password').val();

        $('#email_error').hide();
        $('#password_error').hide();
        $('#emailRequired').hide();
        $('#invalid_email').hide();
        $('#passwordRequired').hide();

        if ($.trim(email) == '') {
            $('#emailRequired').show();
            return false;
        }
        if (IsEmail(email) == false) {
            $('#invalid_email').show();
            return false;
        }

        if ($.trim(password) == '') {
            $('#passwordRequired').show();
            return false;
        }
    });


    $("#login-email").blur(function() {
        var email = $('#login-email').val();

        $('#email_error').hide();
        $('#emailRequired').hide();
        $('#invalid_email').hide();
        if ($.trim(email) == '') {
            $('#emailRequired').show();
            return false;
        }
        if (IsEmail(email) == false) {
            $('#invalid_email').show();
            return false;
        }
    });

    $("#login-password").blur(function() {
        var password = $('#login-password').val();

        $('#password_error').hide();
        $('#passwordRequired').hide();
        if ($.trim(password) == '') {
            $('#passwordRequired').show();
            return false;
        }
    });
    return true;
});


function IsEmail(email) {
    var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!regex.test(email)) {
        return false;
    } else {
        return true;
    }
}

