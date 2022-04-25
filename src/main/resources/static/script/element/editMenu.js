let deleteItem = document.getElementById('deleteItem');
let infoField = document.getElementById('infoField');

deleteItem.addEventListener('click', () => {
    for (let item of itemContainer.children) {
        item.replaceWith(item.cloneNode(true));
    }
    showOnClick(infoField, removeItem);
});

function removeItem(event) {
    if (event !== undefined &&
        (event.target.classList.contains('parentElement') || event.target.parentElement.classList.contains('parentElement'))) {
        let item;
        let type;

        if (event.target.classList.contains('parentElement')) {
            item = event.target;
            type = event.target.getAttribute('type');
        } else if (event.target.parentElement.classList.contains('parentElement')) {
            item = event.target.parentElement;
            type = event.target.parentElement.getAttribute('type');
        }

        $.ajax({
            url: '/generic/remove?type=' + type + '&id=' + item.id,
            type: 'POST',
            success: reloadContent
        });
    }
}