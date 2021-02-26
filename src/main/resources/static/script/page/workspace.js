let popup = document.getElementById('popup');
let addButton = document.getElementById('addButton');
let sessionTitleField = $('#sessionTitleField');

$('#logOutItem').click(logout);
$('#upload').click(openFileChooser);
$('#createButton').click(createSession);

let file;


addButton.addEventListener('click', () => {
    showOnClick(popup);
});


function logout() {
    $.ajax({
        url: '/auth/logout',
        type: 'POST',
        success: () => location.reload()
    });
}

function openFileChooser() {
    var input = document.createElement('input');
    input.type = 'file';

    input.onchange = e => file = e.target.files[0];
    input.click();

}

function createSession() {
    let formData = new FormData;

    formData.append('img', file);
    formData.append('title', sessionTitleField.val());

    $.ajax({
        url: '/session/create',
        contentType: false,
        processData: false,
        data: formData,
        type: 'POST',
        success: () => closePopup(),
        error: xhr => showError(JSON.parse(xhr.responseText).message)
    });
}

function closePopup() {
    popup.style.display = 'none';
    sessionTitleField.val(null);
    hideErrorMessage();
    file = null;
}