
let userList = $("#user-list");

let deleteButton = $("#deleteButton");


let userEditForm = $("#userEditForm");
let editId = $("#editId");
let editUserUsername  = $("#editUserUsername");
let editUserPassword = $("#editUserPassword");
let editUserFirstName = $("#editUserFirstName");
let editUserLasName = $("#editUserLasName");
let editEmail = $("#editEmail");
let editRole = $("#edit-roles");

let editCloseButton = $("#editCloseButton");

let newCloseButton = $("#newCloseButton");
let newUser ;
let newFUser ;
let newFormUser = $("#new-form-user");
let newUserUsername = $("#newUserUsername");
let newUserPassword = $("#newUserPassword");
let newUserFirstName ;
let newUserLasName ;
let newUserEmail ;
let newUserRole = $("#new-user-role");

let newRoleView ;
let AddNewUserInput ;
let allRoles = [];
let newUserFormResetButton = $("#newUserFormResetButton");


$(function () {
    getAllUsers();
    loadAllRoles();
});


function getAllUsers() {
    $.get("/admin/users", function (data) {
        for (let i = 0; i < data.length; i++) {
            let user = data[i];
            let roles = user.roles;
            let rolle = "";
            for (let j = 0; j < roles.length; j++) {
                rolle += roles[j].role + "  ";
            }
            let tr = $("<tr id='tr" + user.id + "'></tr>");
            let td = "";
            td += "<td>" + user.id + "</td>";
            td += "<td id='td" + user.id + "username'>" + user.username + "</td>";
            td += "<td id='td" + user.id + "password'>" + user.password + "</td>";
            td += "<td id='td" + user.id + "first_name'>" + user.first_name + "</td>";
            td += "<td id='td" + user.id + "last_name'>" + user.last_name + "</td>";
            td += "<td id='td" + user.id + "email'>" + user.email + "</td>";
            td += "<td id='td" + user.id + "role' class='text-uppercase'>" + rolle + "</td>";

            td += "<td><div class='btn-group'>" +
                "<button data-target='#editUser' data-toggle='modal' class='btn btn-primary'" +
                "onclick='openEditUserModal(" + user.id + ")' data-userId='" + user.id + "'>Edit</button></div>" +
                "<div class='btn-group'><button class='btn btn-danger' data-userId='" + user.id + "' onclick='removeUser(" + user.id + ")'>Delete</button></div></td>";
            tr.html(td);
            userList.append(tr);


        }
    });
}

function openEditUserModal(id) {

    $.get("/admin/users/"+id, function (user) {
        let id = user.id;
        let username = user.username;
        let password = user.password;
        let first_name = user.first_name;
        let last_name = user.last_name;
        let email = user.email;
        editUserUsername.val(username);
        editUserPassword.val(password);
        editUserFirstName.val(first_name);
        editUserLasName.val(last_name);
        editEmail.val(email);
        editId.val(id);


    });

}



userEditForm.submit(function (event) {
    event.preventDefault();
    $.ajax({
        url: userEditForm.attr("action"),
        method: "PUT",
        data: userEditForm.serialize()
     });
    let rolesString = [];
    for (let role of allRoles) {
        if ($("#editUserRole" + role.role).prop("checked")) {
            rolesString += role.role + " ";
        }
    }

    let id = editId.val();
    let currentTr = $("tr#tr" + id);
    currentTr.empty();
    let newTr = "";
    newTr += "<td>" + id + "</td>";
    newTr += "<td id='td" + id + "username'>" + editUserUsername.val() + "</td>";
    newTr += "<td id='td" + id + "password'>" + editUserPassword.val() + "</td>";
    newTr += "<td id='td" + id + "firstName'>" + editUserFirstName.val() + "</td>";
    newTr += "<td id='td" + id + "lastName'>" + editUserLasName.val() + "</td>";
    newTr += "<td id='td" + id + "email'>" + editEmail.val() + "</td>";


    newTr += "<td id='td" + id + "roles' class='text-uppercase'>" + rolesString + "</td>";

    newTr += "<td><div class='btn-group'>" +
        "<button data-target='#editUser' data-toggle='modal' class='btn btn-primary'" +
        "onclick='openEditUserModal(" + id + ")' data-userId='" + id + "'>Edit</button></div>" +
        "<div class='btn-group'><button class='btn btn-danger' data-userId='" + id + "' onclick='removeUser("+id+")'>Delete</button></div></td>";
    currentTr.html(newTr);
    editCloseButton.trigger("click");


});
function removeUser(id) {
    $("tr#tr" + id).remove();
    $.ajax({
        url: "/admin/users/delete",
        method: "POST",
        data: ({
            id : id
        })
    });
   /* $.get("/admin/users/delete/"+id);
    $.post("/admin/users/delete", id_, function (g) {
        alert(g);
    });*/
}

function loadAllRoles(){
    $.get("/admin/users/roles", function (data) {

        for (let i = 0; i < data.length; i++) {
            allRoles[i] = data[i];
            let role = allRoles[i].role;
            newUserRole.append("<div class='custom-control-inline custom-control custom-switch'>" +
                "<input type='checkbox' class='custom-control-input' name='role' id='newUserRole" + role + "' value='" + role + "'>" +
                "<label for='newUserRole" + role + "' class='font-weight-bold custom-control-label text-uppercase'>" + role + "</label></div>");
            editRole.append("<div class='custom-control-inline custom-control custom-checkbox'>" +
                "<input type='checkbox' class='custom-control-input' name='role' id='editUserRole" + role + "' value='" + role + "'>" +
                "<label for='editUserRole" + role + "' class='font-weight-bold custom-control-label text-uppercase'>" + role + "</label></div>");

        }

    });
}

newFormUser.on("submit", function (event) {
    event.preventDefault();
    let user_ = newFormUser.serialize();
    $.post(newFormUser.attr("action"), user_, function(user){
        let role = "";
        let userRoles = user.roles;
        for (let j = 0; j< userRoles.length; j++) {
            role += userRoles[j].role + " ";
        }
        let newTr = $("<tr id='tr" + user.id + "'></tr>");
        let newTrTd = "";
        newTrTd += "<td>" + user.id + "</td>";
        newTrTd += "<td id='td" + user.id + "username'>" + user.username + "</td>";
        newTrTd += "<td id='td" + user.id + "password'>" + user.password + "</td>";
        newTrTd += "<td id='td" + user.id + "first_name'>" + user.first_name + "</td>";
        newTrTd += "<td id='td" + user.id + "last_name'>" + user.last_name + "</td>";
        newTrTd += "<td id='td" + user.id + "email'>" + user.email + "</td>";
        newTrTd += "<td id='td" + user.id + "roles' class='text-uppercase'>" + role + "</td>";

        newTrTd += "<td><div class='btn-group'>" +
            "<button data-target='#editUser' data-toggle='modal' class='btn btn-primary'" +
            "onclick='openEditUserModal(" + user.id + ")' data-userId='" + user.id + "'>Edit</button></div>" +
            "<div class='btn-group'><button class='btn btn-danger' data-userId='" + user.id + "' onclick='removeUser(" + user.id + ")'>" +
            "Delete</button></div></td>";
        newTr.html(newTrTd);
        userList.append(newTr);
        newCloseButton.trigger("click");
        $("#id").trigger("click");
    });
});


    /*for(let i =0; i<data.length; i++){
    let user = data[i];




    }*/


/*function getUserTable(username, password) {
    $.get("/admin/users/new/" + username +"/" +password, function (user) {

        let role = "";
        let  userRoles = user.roles;
        for (let j = 0; j< userRoles.length; j++) {
            role += userRoles[j].role + " ";
        }
        let newTr = $("<tr id='tr" + user.id + "'></tr>");
        let newTrTd = "";
        newTrTd += "<td>" + user.id + "</td>";
        newTrTd += "<td id='td" + user.id + "username'>" + user.username + "</td>";
        newTrTd += "<td id='td" + user.id + "password'>" + user.password + "</td>";
        newTrTd += "<td id='td" + user.id + "first_name'>" + user.first-name + "</td>";
        newTrTd += "<td id='td" + user.id + "last_name'>" + user.last-name + "</td>";
        newTrTd += "<td id='td" + user.id + "email'>" + user.email + "</td>";
        newTrTd += "<td id='td" + user.id + "roles' class='text-uppercase'>" + role + "</td>";

        newTrTd += "<td><div class='btn-group mr-sm-1'><button class='btn btn-primary' " +
            "data-toggle='modal' data-target='#editUserModal' onclick='openEditUserModal(" + user.id + ")' " +
            "data-userId='" + user.id + "'>Edit</button></div>" +
            "<div class='btn-group'><button class='btn btn-danger' data-userId='" + user.id + "' onclick='removeUser(" + user.id + ")'>" +
            "Delete</button></div></td>";
        newTr.html(newTrTd);
        userList.append(newTr);
        newCloseButton.trigger("click");
    });
}*/


