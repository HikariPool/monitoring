let password = document.getElementById('password');
let repeatedPassword = document.getElementById('repeatedPassword');
let signUpButton = document.getElementById('signUp');

signUpButton.onclick = function () {
    if (password.value === repeatedPassword.value) {
        //todo ajax query for sign up
    } else {
        //todo notify about warning
    }
}
