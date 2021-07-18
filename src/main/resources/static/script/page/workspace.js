$('#logOutItem').click(logout);


function logout() {
    $.ajax({
        url: '/auth/logout',
        type: 'POST',
        success: () => location.reload()
    });
}

