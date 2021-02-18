function showOnClick(element) {
    element.style.display = 'block';
    document.addEventListener('mouseup', (event) => hideIfClickOnOther(event, element));
}

function hideIfClickOnOther(event, element) {
    if (!element.contains(event.target)) {
        element.style.display = 'none';
        document.removeEventListener('click', hideIfClickOnOther);
    }
}