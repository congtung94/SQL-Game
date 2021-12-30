const min = document.getElementById("min");
const sec = document.getElementById("sec");

function pad(val) { return val > 9 ? val : "0" + val; }

const Clock = {
    totalSeconds: 0,
    start: function () {
        if (!this.interval) {
            const self = this;
            this.interval = setInterval(function () {

                self.totalSeconds += 1;

                min.innerHTML = pad(Math.floor(self.totalSeconds / 60 % 60));
                sec.innerHTML = pad(parseInt(self.totalSeconds % 60));
            }, 1000);
        }
    },

    reset: function () {
        Clock.totalSeconds = null;
        clearInterval(this.interval);
        document.getElementById("min").innerHTML = "00";
        document.getElementById("sec").innerHTML = "00";
        delete this.interval;
    },
    pause: function () {
        clearInterval(this.interval);
        delete this.interval;
    },

    resume: function () {
        this.start();
    },

    restart: function () {
        this.reset();
        Clock.start();
    }
};

// Spielzeit anzeigen
let aktuelleZeit = spielstandObject[4];
Clock.totalSeconds = aktuelleZeit;

if (aktuelleZeit != 0){ // das Spiel hat begonnen
    min.innerHTML = pad(Math.floor(aktuelleZeit /60 % 60));
    sec.innerHTML = pad(parseInt(aktuelleZeit % 60));
    console.log(aktuelleZeit);
    Clock.start();
}

function logout (){
    Clock.pause();
    const zeit = {
        time: Clock.totalSeconds,
        aktFrag: aktuelleFrageId
    };

    $.ajax({
        type: "POST",
        url: "/custom_logout",
        data: {
            sek: JSON.stringify(zeit)
        },
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrf_header, csrf_token);
        },
        success: function (response) {
            window.location = "/";
        }
    });
}



//document.getElementById("weiterBtn").addEventListener("click", function () { Clock.start(); });
document.getElementById("pauseButton").addEventListener("click", function () { Clock.pause(); });
document.getElementById("resumeButton").addEventListener("click", function () { Clock.resume(); });
document.getElementById("resetButton").addEventListener("click", function () { Clock.reset(); });
document.getElementById("restartButton").addEventListener("click", function () { Clock.restart(); });
