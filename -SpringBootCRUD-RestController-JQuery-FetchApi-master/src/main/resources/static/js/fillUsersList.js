$(document).ready(function () {
    let token = $('input[name="token"]').val();
    let method = 'GET';
    let myInit = fetchGetDeleteOptions(token, method);
    getAllUsers(myInit);
});

function getAllUsers(myInit) {

    fetch("http://localhost:8090/rest/admin/users", myInit)
        .then(response => {
            response.json()
                .then(data => {
                    let tableBody = $('#fillTableAllUsers tbody');
                    tableBody.empty();
                    $(data).each(function (i, user) {
                        let stringRoles = "";
                        $(user.roles).each(function (i, role) {
                            stringRoles += role.role + " ";
                        });
                        tableBody.append(`<tr> 
                    <td class="font-weight-normal" id="idUserDelete">${user.id}</td> 
                    <td class="font-weight-normal" id="rolesUserDelete">${stringRoles}</td> 
                    <td class="font-weight-normal" id="loginUserDelete">${user.login}</td> 
                    <td class="font-weight-normal" id="passwordUserDelete">${user.password}</td> 
                    <td class="font-weight-normal" id="emailUserDelete">${user.email}</td> 
                    <td><button id="updateButton" class="btn btn-info" role="button" data-toggle="modal" 
                    data-target="#exampleModal" onclick = 'fillModalForm(${user.id})'>Edit</button></td>
                    <td><button id="deleteButton" class="btn btn-info" role="button"  
                    onclick = 'fetchPostDelete(${user.id})'>Delete</button></td>
                    </tr>`);
                    })
                }).catch(error => console.error(error));
        });
}

function fetchGetDeleteOptions(token, method) {
    const myHeaders = new Headers();
    myHeaders.append('Authorization', token);
    myHeaders.append('Accept', 'application/json');
    myHeaders.append('Content-Type', 'application/json');
    const myInit = {
        method: method,
        headers: myHeaders,
        mode: 'cors'
    };
    return myInit;
}

function fetchPutPosteOptions(token, method, data) {
    const myHeaders = new Headers();
    myHeaders.append('Authorization', token);
    myHeaders.append('Accept', 'application/json');
    myHeaders.append('Content-Type', 'application/json');
    const myInit = {
        method: method,
        headers: myHeaders,
        mode: 'cors',
        body: JSON.stringify(data)
    };
    return myInit;
}

function fillJson(role, roleValue, email, login, password) {
    let data = {
        email: email,
        login: login,
        password: password,
        roles: [{
            id: role,
            role: roleValue
        }]
    };
    return data;
}