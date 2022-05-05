let playButton = document.getElementById('playTrack');
let nextButton = document.getElementById('nextTrack');
let prevButton = document.getElementById('prevTrack');
let progressBar = document.getElementById('progressIndicator');
let trackIcon = document.getElementById('trackIcon');
let musicPlayerWrapper = document.getElementById('musicPlayerWrapper');
let videoWrapper = document.getElementById('videoWrapper');

let video = document.getElementById('videoPlayer');
let audio = new Audio();

// audio.onplay = () => musicPlayerWrapper.style = "display: block";
// video.onplay = () => videoWrapper.style = 'display: block';

function playContent(path, onEnd, onUpdate, imagePath) {

    let player;

    if (path !== undefined && path.includes('.mp3')) {
        player = audio;
        showAndHighOnClickOut(musicPlayerWrapper, e => stopPlay(audio));
    } else if (path !== undefined && (path.includes('.mp4')
        || path.includes('.mov'))) {
        player = video;
        showAndHighOnClickOut(videoWrapper, e => stopPlay(video));
    }


    if (imagePath !== null) {
        trackIcon.src = imagePath;
    }
    if (path !== null) {
        player.src = path;
    }
    player.play();
    //todo change play icon
    //todo change preview playing track
    player.ontimeupdate = event => {
        updateProgressBar(event.target);
        if (onUpdate !== undefined) {
            onUpdate(event.target);
        }
    };
    player.onended = () => {
        //todo change play icon
        if (onEnd !== undefined) {
            onEnd();
        }
    };
    playButton.onclick = () => stopPlay(player, onEnd, onUpdate);
}

function stopPlay(player, onEnd, onUpdate) {
    player.ontimeupdate = null;
    player.pause();
    playButton.onclick = () => playContent(null, onEnd, onUpdate, null);
}

function updateProgressBar(audio) {
    let percent = audio.currentTime * 100 / audio.duration;
    progressBar.style = 'width: ' + percent + '%';
}
