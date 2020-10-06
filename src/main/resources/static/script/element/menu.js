const menu = document.getElementById('menu');
const userIcon = document.getElementById('userIcon');

userIcon.addEventListener('click', openMenu);


function openMenu() {
    menu.style.display = 'block';
    document.addEventListener('mouseup', hideMenu);
}

function hideMenu(event) {
    if (!menu.contains(event.target)) {
        menu.style.display = 'none';
        document.removeEventListener('click', hideMenu);
    }
}

//todo logout