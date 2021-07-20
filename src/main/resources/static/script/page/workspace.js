$('#logOutItem').click(logout);
let iframe = document.getElementById('workspace');

window.addEventListener('message', (path) => navigateIframe(path.data));


function logout() {
    $.ajax({
        url: '/auth/logout',
        type: 'POST',
        success: () => location.reload()
    });
}

function navigateIframe(path) {
    iframe.src = path;
}