function fillModalForm(id) {
    let token = $('input[name="token"]').val();
    let method='GET';
    let myInit=window.fetchGetDeleteOptions(token,method);
    let url = `http://localhost:8090/rest/admin/updateUser/${id}`;

    fetch(url, myInit)
        .then(response => {
            response.json()
                .then(data => {
                    $('#inputModalId').val(data.id);
                    $('#inputModalEmail').val(data.email);
                    $('#inputModalLogin').val(data.login);
                    $('#inputModalPassword').val(data.password);
                });
        }).catch(error => console.error(error));
}

