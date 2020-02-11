function fetchPostDelete(id) {
    let token = $('input[name="token"]').val();
    let url = `http://localhost:8090/rest/admin/deleteUser/${id}`;
    let method='DELETE';
    let myInit=window.fetchGetDeleteOptions(token,method);

    fetch(url, myInit)
        .then(() => {
            document.location.reload();
        }).catch(error => console.error(error));
}


