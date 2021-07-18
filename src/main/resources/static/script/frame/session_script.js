let popup = document.getElementById('popup');
let addButton = document.getElementById('addButton');
let sessionTitleField = $('#sessionTitleField');
let itemContainer = document.getElementById('listItemContainer');


$('#upload').click(openFileChooser);
$('#createButton').click(createSession);


reloadSessions();


let file;


addButton.addEventListener('click', () => {
    showOnClick(popup);
});

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
        success: () => {
            closePopup();
            reloadSessions();
        },
        error: xhr => showError(JSON.parse(xhr.responseText).message)
    });
}

function closePopup() {
    popup.style.display = 'none';
    sessionTitleField.val(null);
    hideErrorMessage();
    file = null;
}


function reloadSessions() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/session/all',
        type: 'GET',
        success: data => showSessions(data),
    });
}

function showSessions(sessions) {
    for (let i = 0; i < sessions.length; i++) {
        let div = document.createElement('div');
        div.className = 'gridListItem';
        div.id = sessions[i].id;


        let img = document.createElement('img');
        img.className = 'gridListItemImage';
        img.src = sessions[i].imagePath;

        let h3 = document.createElement('h3');
        h3.className = 'gridListItemTitle';
        h3.textContent = sessions[i].title;

        div.appendChild(img);
        div.appendChild(h3);
        itemContainer.appendChild(div);
    }
}
