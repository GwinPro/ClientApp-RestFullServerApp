$(document).ready(function () {
    let token = $('input[name="token"]').val();
    let url = `http://localhost:8090/rest/admin/addUser`;
    let method='POST';




    $('#addUserButton').click(

        () => {

            let role = $('#newUser-role').val();
            let roleValue = $('#newUser-role option:selected').text();
            let email= $('#inputEmail').val();
            let login= $('#inputLogin').val();
            let password= $('#inputPassword').val();

            let addData = window.fillJson(role,roleValue,email,login,password);
            let myInit=window.fetchPutPosteOptions(token,method,addData);

            fetch(url, myInit).then(() => {
                document.location.reload();
            }).catch(error => console.error(error));
        });
});

