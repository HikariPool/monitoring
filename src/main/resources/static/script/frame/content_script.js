let popup = document.getElementById('popup');
let addButton = document.getElementById('addButton');
let titleField = $('#title');
let itemContainer = document.getElementById('listItemContainer');


$('#upload').click(openFileChooser);
$('#createButton').click(addContentItem);


reloadContent();


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

function addContentItem() {
    let formData = new FormData;

    formData.append('img', file);
    formData.append('title', titleField.val());

    $.ajax({
        url: '/session/content/create',
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
        url: '/session/get?session_id=' + getParam('session_id'),
        type: 'GET',
        success: data => showContentItems(data)
    });
}

function getParam(title) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(title);
}

function showContentItems(session) {
    titleField.html(session.title);

    let contentItems = session.contentItems;
    for (let i = 0; i < contentItems.length; i++) {
        let div = document.createElement('div');
        div.className = 'lineListItem';
        div.id = contentItems[i].sourcePath;


        let img = document.createElement('img');
        img.className = 'lineListItemImage';
        img.src = contentItems[i].previewPath;

        let h3 = document.createElement('h3');
        h3.className = 'lineListItemTitle';
        h3.textContent = contentItems[i].title;

        div.appendChild(img);
        div.appendChild(h3);
        itemContainer.appendChild(div);

        div.onclick = () =>
            window.parent.playAudio(
                '/stream/get/' + contentItems[i].sourcePath, undefined, audio => window.top.sync(audio, session.id), contentItems[i].previewPath);
    }
}

function playNextItem() {

}
