$(function () {
    var email =$("#email");
    var password =$("#password");
    var repPassword=$("#rep-password")
    var firstName = $("#first-name")
    var lastName = $("#last-name")
    var phone = $("#phone")
    $("#email").focus(function () {
        $("#email-lbl").css({"top":"-10px","font-size":"10px"});
    })
    email.blur(function () {
        if($("#email").val()=="")
        {
            $("#email-lbl").css({"top":"9px","font-size":"16px"});
        }
    })
    password.focus(function () {
        $("#pass-lbl").css({"top":"-10px","font-size":"10px"})
    })
    password.blur(function () {
        if($("#password").val()=="")
        {
            $("#pass-lbl").css({"top":"9px","font-size":"16px"})
        }
    })
    repPassword.focus(function () {
        $("#rep-lbl").css({"top":"-10px","font-size":"10px"})
    })
    repPassword.blur(function () {
        if($("#rep-password").val()=="")
            $("#rep-lbl").css({"top":"9px","font-size":"16px"})
    })
    firstName.focus(function () {
        $("#first-name-lbl").css({"top":"-10px","font-size":"10px"})
    })
    firstName.blur(function () {
        if($("#first-name").val()=="")
            $("#first-name-lbl").css({"top":"9px","font-size":"16px"})
    })
    lastName.focus(function () {
        $("#last-name-lbl").css({"top":"-10px","font-size":"10px"})
    })
    lastName.blur(function () {
        if($("#last-name").val()=="")
            $("#last-name-lbl").css({"top":"9px","font-size":"16px"})
    })
    phone.focus(function () {
        $("#phone-lbl").css({"top":"-10px","font-size":"10px"})
    })
    phone.blur(function () {
        if($("#phone").val()=="")
            $("#phone-lbl").css({"top":"9px","font-size":"16px"})
    })
    $("lable").click(function () {
        $(this).siblings("input").focus();
    })

    $("#register-div").click(function () {
        $("#line").css("right","45px")
        $(".login").css({"height":"500px" , "top":"calc(50% - 250px)"})
        $("#reg-div").css("display","inline-block")
        $("#reg-btn").css("display","inline-block")
        $("#login-btn").css("display","none")
        $("#first-name-div").css("display","inline-block")
        $("#last-name-div").css("display","inline-block")
        $("#phone-div").css("display","inline-block")
    })
    $("#login-div").click(function () {
        $("#line").css("right","-10px")
        $(".login").css({"height":"300px" , "top":"calc(50% - 150px)"})
        $("#reg-div").css("display","none")
        $("#reg-btn").css("display","none")
        $("#login-btn").css("display","inline-block")
        $("#first-name-div").css("display","none")
        $("#last-name-div").css("display","none")
        $("#phone-div").css("display","none")
    })
})
