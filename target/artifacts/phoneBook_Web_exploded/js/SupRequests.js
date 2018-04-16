$(function () {
    var token = extractToken();
    loadUsers("POST", "api/UserServices/getAll");
    loadContacts("POST", "api/Contact/getAll");
    $(".myTable").on('click', '.delete-icon', function () {
        var id = $(this).parent("td").siblings("td:eq(0)").html()
        var name = $(this).parent("td").siblings("td:eq(1)").html()
        if (confirm(name + " حذف خواهد شد. مطمئن هستید؟"))
            deleteContact("GET", "api/Contact/delete/" + id, true)
    })
    $(".myTable").on('click', '.edit-icon', function () {
        var contact = getContact(this);
        if (confirm(contact.firstName + " ویرایش خواهد شد. مطمئن هستید؟")) {
            updateContact('POST', 'api/Contact/update', contact, this)
        }
    });

    //appending input tags to td's when clicking on them
    $(".myTable").on('click', 'tr td:gt(0)', function () {
        if ($(this).children('input').length == 0 &&
            !$(this).is(':last-child') &&
            !$(this).is(':first-child'))
            $(this).append("<input class='form-control myInput'>")
    });

    $('.add-done-img-btn').click(function () {
        var values = [];
        for (var i = 0; i <= 4; i++) {
            values[i] = $(this).parent('div').siblings('table').find('tr:last-child').find('input:eq(' + i + ')').val();
        }
        var contact = {};
        console.log(values);
        contact.id = $(this).parent('div').siblings('table').find('tr:last-child').children('td:eq(0)').html();
        contact.firstName = values[0];
        contact.lastName = values[1];
        contact.email = values[2];
        contact.mobile = values[3];
        contact.home = values[4];
        console.log(contact);
        if (confirm(contact.firstName + "اضافه خواهد شد مطمئن هستید؟"))
            addContact('POST', 'api/Contact/add', contact, this);
        else {
            $(this).parent('div').siblings('table').find('tr:last-child').find('input').remove();
            $(this).parent('div').siblings('table').find('tr:last-child').remove()
        }
        $(this).parent('div').css('width', '0px');
        $(this).css('display', 'none')
    });
    $('.add-img-btn').click(function () {
        var index = $(this).parent('div').siblings('table').find('tr:last-child').children("td:first-child").html();
        index++;
        $(this).parent('div').css('width', '70px');
        $(this).siblings('img').css('display', 'inline-block');
        var row = $('<tr>');
        row.append('<td>' + (index) + '</td>')
            .append('<td><input class="form-control myInput"></td>')
            .append('<td><input class="form-control myInput"></td>')
            .append('<td><input class="form-control myInput"></td>')
            .append('<td><input class="form-control myInput"></td>')
            .append('<td><input class="form-control myInput"></td>')
            .append($("<td><img class='icon delete-icon' src='../icons/dustbin.png' alt='delete'>" +
                "<img class='icon edit-icon' src='../icons/settings.png' alt='edit'></td>"));
        $(this).parent('div').siblings('table').append(row)
    });

    //Contacts Requests
    function loadContacts(method, url) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
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
                        .append($("<td><img class='icon delete-icon' src='../icons/dustbin.png' alt='delete'>" +
                            "<img class='icon edit-icon' src='../icons/settings.png' alt='edit'></td>"));
                    //$("#last_row").before(row);
                    $(".myTable:eq(0)").append(row)
                }
            }
        }
        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        //xhttp.send("ok");
    };

    function updateContact(method, url, contact, btn) {
        var xhttp = new XMLHttpRequest();
        xhttp.open(method, url, true);
        xhttp.setRequestHeader('Content-type', 'application/json');
        xhttp.setRequestHeader('AUTHORIZATION', token);
        xhttp.send(JSON.stringify(contact));
        xhttp.onreadystatechange = function () {
            if (this.status == 200 , this.readyState == 4) {
                $(btn).parent('td').siblings('td:eq(1)').text(contact.firstName);
                $(btn).parent('td').siblings('td:eq(2)').text(contact.lastName)
                $(btn).parent('td').siblings('td:eq(3)').text(contact.email)
                $(btn).parent('td').siblings('td:eq(4)').text(contact.mobile)
                $(btn).parent('td').siblings('td:eq(5)').text(contact.home)
            }
        }

    }

    function deleteContact(method, url) {
        var xhttp = new XMLHttpRequest();
        xhttp.open(method, url, true);
        xhttp.setRequestHeader("AUTHORIZATION", token);
        xhttp.send("ok");

        xhttp.onreadystatechange = function () {
            if (this.status == 200, this.readyState == 4) {
                alert("Contact Deleted!")
            }
            else {
                alert("ERROR CODE: " + this.status)
            }
        }
    }

    function addContact(method, url, contact, btn) {
        var xhttp = new XMLHttpRequest();
        xhttp.open(method, url, true);
        xhttp.setRequestHeader('Content-type', 'application/json');
        xhttp.setRequestHeader('Authorization', token);
        xhttp.send(JSON.stringify(contact))

        var lastRow = $(btn).parent('div').siblings('table').find('tr:last-child')
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 201) {
                lastRow.find('input').remove()
                lastRow.find('td:eq(1)').text(contact.firstName)
                lastRow.find('td:eq(2)').text(contact.lastName)
                lastRow.find('td:eq(3)').text(contact.email)
                lastRow.find('td:eq(4)').text(contact.mobile)
                lastRow.find('td:eq(5)').text(contact.home)
            }
        }
    }

    //Users Requests
    function loadUsers(method, url) {
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
                        .append($("<td>" + data[i].username + "</td>"))
                        .append($("<td>" + data[i].role.getName() + "</td>"))
                        .append($("<td><img class='icon delete-icon' src='../icons/dustbin.png' alt='delete'>" +
                            "<img class='icon edit-icon' src='../icons/settings.png' alt='edit'></td>"));
                    $(".myTable:eq(1)").append(row)
                }
            }
        }
        xhttp.open(method, url, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.setRequestHeader("Authorization", token);
        xhttp.send("ok");
    };


    /*$.ajax({
        url:"api/UserServices/getAll",
        type:"POST",
        headers:{"Authorization":token},
        dataType:"application/json",
        success:alert('success'),
        error:alert('error')
    })*/
    function setUserRole() {

    }

    function deleteUser() {

    }

    function getContact(btn) {
        var values = []
        for (var i = 0; i <= 5; i++) {
            if ($(btn).parent('td').siblings('td:eq(' + i + ')').children('input').length == 0) {
                values[i] = $(btn).parent('td').siblings('td:eq(' + i + ')').html();
                alert(values[i])
            }
            else {
                values[i] = $(btn).parent('td').siblings('td:eq(' + i + ')').children('input').val();
            }
        }
        var contact = {}
        contact.id = values[0];
        contact.firstName = values[1]
        contact.lastName = values[2]
        contact.email = values[3]
        contact.mobile = values[4]
        contact.home = values[5]
        console.log(contact);

        //remove all input tags that we append them into td tags
        $(btn).parent('td').siblings('td').children('input').remove();

        return contact;
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