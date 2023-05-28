let saveButton = document.getElementById('saveButton');
let title = $('#title');
let itemContainer = document.getElementById('listItemContainer');
let editButton = document.getElementById('editButton');
let editMenu = document.getElementById('editMenu');
let workspaceHead = document.getElementById('workspaceHead');


$('#createButton').click(addContentItem);


reloadContent();



saveButton.addEventListener('click', () => {
    saveButton.hidden = true

});


function addContentItem() {
    let formData = new FormData;
    let img;
    let media;

    for (let file of files) {
        if (file !== undefined && file != undefined && file !== undefined &&
            (file.name.indexOf('.jpg') !== -1 || file.name.indexOf('.png') !== -1)) {
            img = file;
        }

        if (file !== undefined && file != undefined && file !== undefined &&
            (file.name.indexOf('.mp3') !== -1 || file.name.indexOf('.mp4') !== -1 || file.name.indexOf('.mov') !== -1)) {
            media = file;
        }
    }

    formData.append('img', img);
    formData.append('media', media);
    formData.append('title', titleField.val());

    $.ajax({
        url: '/session/content/create?session_id=' + getParam('session_id'),
        contentType: false,
        processData: false,
        data: formData,
        type: 'POST',
        success: () => {
            reloadContent();
        },
        error: xhr => showError(JSON.parse(xhr.responseText).message)
    });
}

function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/dashboard/find?id=' + getParam('dashboard_id'),
        type: 'GET',
        success: data => showContentItems(data)
    });
}

function getParam(title) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(title);
}

function showContentItems(dashboard) {
    workspaceHead.setAttribute('dashboard_id', dashboard.id);

    title.html(dashboard.name);

    let dashboardResDtos = dashboard.dashboardResDtos;
    for (let i = 0; i < dashboardResDtos.length; i++) {
        let div = document.createElement('div');
        div.className = 'lineListItem parentElement';
        div.id = dashboardResDtos[i].id;
        div.setAttribute('type', 'contentItem');



        itemContainer.appendChild(div);
    }
}

editButton.addEventListener('click', () => {
    showAndHighOnClickOut(editMenu);
    editMenu.addEventListener('click', () => editMenu.style.display = 'none');
});
