$(document).ready(function () {
    $('#updateUser').click(() =>{
        let token = $('input[name="token"]').val();
        let id = $('#inputModalId').val();
        let url = `http://localhost:8090/rest/admin/updateUser/${id}`;
        let method='PUT';
        let role = $('#inputModalRole').val();
        let roleValue = $('#inputModalRole option:selected').text();
        let email= $('#inputModalEmail').val();
        let login= $('#inputModalLogin').val();
        let password= $('#inputModalPassword').val();

        let addData = window.fillJson(role,roleValue,email,login,password);
        let myInit=window.fetchPutPosteOptions(token,method,addData);

        fetch(url, myInit).then(() => {
            document.location.reload();
        }).catch(error => console.error(error));
    });
});

