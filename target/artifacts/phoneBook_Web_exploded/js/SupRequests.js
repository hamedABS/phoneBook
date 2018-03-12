$(function () {
    var token= extractToken();

    function loadContacts(method,url) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = JSON.parse(this.response);
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    var row = $("<tr>")
                    row.append($("<td>" + data[i].id + "</td>"))
                        .append($("<td>" + data[i].firstName + "</td>"))
                        .append($("<td>" + data[i].lastName + "</td>"))
                        .append($("<td>" + data[i].email + "</td>"))
                        .append($("<td>" + data[i].mobile + "</td>"))
                        .append($("<td>" + data[i].home + "</td>"))
                        .append($("<td><img class='icon delete-icon' src='../dustbin.png' alt='delete'>" +
                            "<img class='icon edit-icon' src='../settings.png' alt='edit'></td>"));
                    $("#last_row").before(row);
                }
            }
        }
        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.setRequestHeader("AUTHORIZATION",token);
        xhttp.send("ok");
    }
    loadContacts("POST","api/Contact/getAll");

    $(".myTable").on('click','.delete-icon',function () {
        var id = $(this).parent("td").siblings("td:eq(0)").html()
        var name = $(this).parent("td").siblings("td:eq(1)").html()

        if(confirm(" حذف خواهد شد. مطمئن هستید؟"+name))
        deleteContact("GET","api/Contact/delete/"+id,true)
    })

    function deleteContact(method , url) {
        var xhttp = new XMLHttpRequest();
        xhttp.open(method, url, true);
        xhttp.setRequestHeader("AUTHORIZATION",token);
        xhttp.send("ok");

        xhttp.onreadystatechange = function () {
            if(this.status==200,this.readyState==4){
                alert("Contact Deleted!")
            }
            else {
                alert("ERROR CODE: "+this.status)
            }
        }

    }


    function extractToken() {
        var query = window.location.search.substring(1);
        console.log(query);
        var parms = query.split('&');
        var temp = parms[0];
        var param = temp.split('=');
        return param[1];
    }
})