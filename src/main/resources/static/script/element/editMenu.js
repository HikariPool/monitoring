let runButton = document.getElementById('runButton');
let saveButton = document.getElementById('saveButton');
let editItem = document.getElementById('editItem');
let queryInput = document.getElementById('queryInput');


editItem.onclick = () => {
    runButton.hidden = true
    saveButton.hidden = false
    queryInput.hidden = false
}

saveButton.onclick = () => {
    runButton.hidden = false
    saveButton.hidden = true
    queryInput.hidden = true

    saveQuery()
}

function saveQuery() {
    $.ajax({
        url: '/dashboard/updateQuery?id=' + getParam("dashboard_id"),
        data: {
            "query": queryInput.value
        },
        type: 'POST'
    });
}


runButton.addEventListener('click', () => {
    // $.ajax({
    //     url: '/session/people?session_id=' + workspaceHead.getAttribute('session_id') + '&email=' + addPeopleField.value,
    //     type: 'POST',
    //     success: () => addPeopleField.value = ''
    // });
});


