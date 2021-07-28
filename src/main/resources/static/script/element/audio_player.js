let playButton = document.getElementById('playTrack');
let nextButton = document.getElementById('nextTrack');
let prevButton = document.getElementById('prevTrack');
let progressBar = document.getElementById('progressIndicator');

let audio = new Audio();

function playAudio(id, onEnd) {
    audio.src = '/stream/get/' + id;
    audio.play();
    //todo change play icon
    audio.ontimeupdate = event => {
        updateProgressBar(event.target);
        sync(event.target);
    };
    audio.onended = event => {
        //todo change play icon
        if (onEnd !== undefined) {
            onEnd();
        }
    };
}

function updateProgressBar(audio) {
    let percent = audio.currentTime * 100 / audio.duration;
    progressBar.style = 'width: ' + percent + '%';
}

function sync(audio) {
    //todo  sync client side
}