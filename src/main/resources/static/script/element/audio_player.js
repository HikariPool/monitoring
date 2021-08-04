let playButton = document.getElementById('playTrack');
let nextButton = document.getElementById('nextTrack');
let prevButton = document.getElementById('prevTrack');
let progressBar = document.getElementById('progressIndicator');

let audio = new Audio();

function playAudio(path, onEnd, onUpdate) {
    audio.src = path;
    audio.play();
    //todo change play icon
    //todo change preview playing track
    audio.ontimeupdate = event => {
        updateProgressBar(event.target);
        if (onUpdate !== undefined) {
            onUpdate(event.target);
        }
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