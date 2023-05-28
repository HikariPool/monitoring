let popup = document.getElementById('popup');
let addButton = document.getElementById('addButton');
let editButton = document.getElementById('editButton');
let editMenu = document.getElementById('editMenu');
let titleField = $('#titleField');
let itemContainer = document.getElementById('listItemContainer');


$('#upload').click(openFileChooser);
$('#createButton').click(createSession);


reloadContent();


let file;


addButton.addEventListener('click', () => {
    showAndHighOnClickOut(popup);
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
    formData.append('title', titleField.val());

    $.ajax({
        url: '/session/create',
        contentType: false,
        processData: false,
        data: formData,
        type: 'POST',
        success: () => {
            closePopup();
            reloadContent();
        },
        error: xhr => showError(JSON.parse(xhr.responseText).message)
    });
}

function closePopup() {
    popup.style.display = 'none';
    titleField.val(null);
    hideErrorMessage();
    file = null;
}


function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/dashboard/all',
        type: 'GET',
        success: data => showSessions(data)
    });
}

function showSessions(dashboards) {
    for (let i = 0; i < dashboards.length; i++) {
        let div = document.createElement('div');
        div.className = 'gridListItem parentElement';
        div.id = dashboards[i].id;
        div.setAttribute('type', 'session');


        let count = document.createElement('h3');
        count.className = 'gridListItemCount';
        count.textContent = dashboards[i].countRes;

        let h3 = document.createElement('h3');
        h3.className = 'gridListItemTitle';
        h3.textContent = dashboards[i].name;

        div.appendChild(count);
        div.appendChild(h3);
        itemContainer.appendChild(div);

        div.onclick = () => openSession(div.id);
    }
}

function openSession(sessionId) {
    window.parent.postMessage('/frame/content_frame?session_id=' + sessionId);
}

editButton.addEventListener('click', () => {
    showAndHighOnClickOut(editMenu);
});
