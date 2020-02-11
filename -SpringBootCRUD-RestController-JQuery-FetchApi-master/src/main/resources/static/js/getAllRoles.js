$(document).ready(function () {
    let token = $('input[name="token"]').val();
    let url = `http://localhost:8090/rest/admin/roles`;
    let method='GET';
    let myInit=window.fetchGetDeleteOptions(token,method);

    fetch(url, myInit)
        .then(response => {
            response.json().then(function (data) {
                let selectBody = "";
                selectBody = $('#newUser-role');
                $(data).each(function (i, role) {
                    selectBody.append(`<option value="${role.id}">${role.role}</option>`);
                })
            })
        }).catch(error => console.error(error));

});
