let password = document.getElementById('password');
let repeatedPassword = document.getElementById('repeatedPassword');
let signUpButton = document.getElementById('signUp');

signUpButton.onclick = function () {
    if (passwordsIsRepeatable()) {
        //todo ajax query for sign up
    } else {
        //todo notify about warning
    }
}

function passwordsIsRepeatable() {
    return password.value === repeatedPassword.value;
}
