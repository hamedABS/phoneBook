$(function () {
    var xhttp = new XMLHttpRequest();
    var token;

    $("#login-btn").click(function () {
        xhttp.open("POST", "/api/loginRegister/authenticate", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        var person = {}
        person.username = $("#email").val();
        person.password = $("#password").val();
        xhttp.send(JSON.stringify(person));
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var token = xhttp.response;
                var role = xhttp.getResponseHeader("authentication")
                console.log(role)
                if(role=="SuperUser"){
                    window.location.replace("../superUserPage.html?token="+token);
                }
                else if(role=="Admin") alert("Admin")
                else if(role=="Guest") alert("Guest")
                else if(role=="User") alert("User")
                //window.location.replace("managerPage.html");
            }
        }
    })
    /*function parseJwt(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    };*/

})

