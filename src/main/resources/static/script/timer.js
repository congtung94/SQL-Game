/*
var minutesLabel = document.getElementById("minutes");
var secondsLabel = document.getElementById("seconds");
var totalSeconds = 0;
setInterval(setTime, 1000);

function setTime() {
    ++totalSeconds;
    secondsLabel.innerHTML = pad(totalSeconds % 60);
    minutesLabel.innerHTML = pad(parseInt(totalSeconds / 60));
}

function pad(val) {
    var valString = val + "";
    if (valString.length < 2) {
        return "0" + valString;
    } else {
        return valString;
    }
}*/

var Clock = {
    totalSeconds: 0,
    start: function () {
        if (!this.interval) {
            var self = this;
            function pad(val) { return val > 9 ? val : "0" + val; }
            this.interval = setInterval(function () {
                self.totalSeconds += 1;

                document.getElementById("min").innerHTML = pad(Math.floor(self.totalSeconds / 60 % 60));
                document.getElementById("sec").innerHTML = pad(parseInt(self.totalSeconds % 60));
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


document.getElementById("weiterBtn").addEventListener("click", function () { Clock.start(); });
document.getElementById("pauseButton").addEventListener("click", function () { Clock.pause(); });
document.getElementById("resumeButton").addEventListener("click", function () { Clock.resume(); });
document.getElementById("resetButton").addEventListener("click", function () { Clock.reset(); });
document.getElementById("restartButton").addEventListener("click", function () { Clock.restart(); });
