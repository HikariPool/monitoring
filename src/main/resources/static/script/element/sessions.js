let itemContainer = document.getElementById('listItemContainer');

reloadSessions();

function reloadSessions() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/session/all',
        type: 'GET',
        success: data => showSessions(data),
    });
}

function showSessions(sessions) {//need to get norm json values
    for (let i = 0; i < sessions.length; i++) {
        let div = document.createElement('div');
        div.className = 'gridListItem';
        div.id = sessions[i].id;


        let img = document.createElement('img');
        img.className = 'gridListItemImage';
        img.src = sessions[i].imagePath;

        let h3 = document.createElement('h3');
        h3.className = '';
        h3.textContent = sessions[i].title;

        div.appendChild(img);
        div.appendChild(h3);
        itemContainer.appendChild(div);
    }
}