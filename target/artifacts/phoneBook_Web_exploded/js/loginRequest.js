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
                else if(role=="Admin") {
                    window.location.replace("../AdminPage.html?token="+token)
                }
                else if(role=="Guest"){
                    window.location.replace()
                }
                else if(role=="User") {
                    window.location.replace()
                }
            }
        }
    })

})

