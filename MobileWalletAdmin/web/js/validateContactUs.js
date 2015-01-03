/* 
 * Client side validations for user contactUs form.(contactUsBody.jsp)
 */


$(document).ready(function () {

    $("#typequery").change(function () {
        $('#typequeryRequired').hide();
        var typequery = $('#typequery').val();
        if (typequery == '-1') {
            $('#typequeryRequired').show();
            return false;
        }
    });

    $("#question").blur(function () {
        $('#questionRequired').hide();
        var question = $('#question').val();

        if ($.trim(question) == '') {
            $('#questionRequired').show();
            return false;
        }
    });

    $("#optiona").blur(function () {
        var optiona = $('#optiona').val();
        $('#optionaRequired').hide();

        if ($.trim(optiona) == '') {
            $('#optionaRequired').show();
            return false;
        }
    });

    $("#optionb").blur(function () {
        $('#optionbRequired').hide();

        var optionb = $('#optionb').val();
        if ($.trim(optionb) == '') {
            $('#optionbErrors').show();
            $('#optionbRequired').show();
            return false;
        }
    });

    $("#optionc").blur(function () {
        $('#optioncRequired').hide();
        var optionc = $('#optionc').val();

        if ($.trim(optionc) == '') {
            $('#optioncRequired').show();
            return false;
        }
    });

    $("#optiond").blur(function () {
        $('#optiondRequired').hide();
        var optiond = $('#optiond').val();

        if ($.trim(optiond) == '') {
            $('#optiondRequired').show();
            return false;
        }
    });

    $("#answer").blur(function () {
        $('#answerRequired').hide();
        var answer = $('#answer').val();

        if ($.trim(answer) == '') {
            $('#answerRequired').show();
            return false;
        }
    });

    $("#explanation").blur(function () {
        $('#explanationRequired').hide();
        var explanation = $('#explanation').val();

        if ($.trim(explanation) == '') {
            $('#explanationRequired').show();
            return false;
        }
    });

    var options = {
        beforeSubmit: showRequest,
        success: showResponse
    };
    $('#submitQuestionForm').ajaxForm(options);
});

function showRequest(formData) {

    $('#typequeryRequired').hide();
    $('#questionRequired').hide();
    $('#optionaRequired').hide();
    $('#optionbRequired').hide();
    $('#optioncRequired').hide();
    $('#optiondRequired').hide();
    $('#answerRequired').hide();
    $('#explanationRequired').hide();

    var typequery = $('#typequery').val();
    var question = $('#question').val();
    var optiona = $('#optiona').val();
    var optionb = $('#optionb').val();
    var optionc = $('#optionc').val();
    var optiond = $('#optiond').val();
    var answer = $('#answer').val();
    var explanation = $('#answer').val();


    if (typequery == '-1') {
        $('#typequeryRequired').show();
        return false;
    }

    if ($.trim(question) == '') {
        $('#questionRequired').show();
        return false;
    }

    if ($.trim(optiona) == '') {
        $('#optionaRequired').show();
        return false;
    }

    if ($.trim(optionb) == '') {
        $('#optionbRequired').show();
        return false;
    }

    if ($.trim(optionc) == '') {
        $('#optioncRequired').show();
        return false;
    }

    if ($.trim(optiond) == '') {
        $('#optiondRequired').show();
        return false;
    }

    if ($.trim(answer) == '') {
        $('#answerRequired').show();
        return false;
    }

    if ($.trim(explanation) == '') {
        $('#explanationRequired').show();
        return false;
    }

    $('#submitQuestionButton').val("Processing...").prop('disabled', true);
    var queryString = $.param(formData);

    return true;
}
function showResponse(responseText) {
    $('#submitQuestionButton').val("Submit").prop('disabled', false);
    $('#submitQuestionButton').hide();
    if ($.trim(responseText) !== '{"login":"login"}') {
        if ($.trim(responseText) === '1') {
            $('#successMsg').show();
            $(window).scrollTop("0");
        }
    } else {
        location.href = "/loginPage";
    }
}