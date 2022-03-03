
const listFragebObject = Object.values(listFragen);
const spielstandObject = Object.values(spielstand);


const frageAnzahl = listFragen.length;

const weiterBtn = document.getElementById("weiterBtn");
const ausfuhrenBtn = document.getElementById("ausfuehrenBtn");
const uberspringenBtn = document.getElementById("ueberspringen")
const frageText = document.getElementById("frageText");
const tipsText = document.getElementById("tipsText");


// level, punkte , aktuelle Frage von Spieler
let aktuelleFrageId = spielstandObject[1];
let level = spielstandObject[2];
let punkte = spielstandObject[3];

// wenn es um eine übersprungene Frage, wird es bemerkt
let istUbersprungenFrage = false;
if (ubersprungenFragen.includes(aktuelleFrageId)){
    $('#mitteilungModal').modal('show').find(".modal-title").text("Mitteilung");
    $('#mitteilungModal').modal('show').find(".modal-body").text("Folgende sind die Fragen, die Sie übersprungen haben ");
    istUbersprungenFrage = true;
}
// frageText anzeigen
frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1];
// Tips anzeigen
tipsText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[4];

setVisibilityWeiterBtn();
setVisibilityUberspringenBtn();

function nachsteFrage() {
    // wenn aktuelle Frage eine übersprungene Frage ist, dann die nächste Frage ist in der Liste von übersprungenen Fragen
    if (istUbersprungenFrage){
        // die aktuelle Frage in der übersprungenen Fragen liste entfernen
        ubersprungenFragen.shift();
        // das Spiel gewonnen
        if (ubersprungenFragen.length === 0){
            ausfuhrenBtn.style.visibility = "hidden";
            weiterBtn.style.visibility = "hidden";;
            alert("Glückwunsch, du hast das Spiel gewonnen !!!");
        }
        else {
            // die nächste Frage ist die erste Frage in übersprungener Fragenliste
            aktuelleFrageId = ubersprungenFragen[0];
            setViewForNextQuestion();
        }
    }
    else {
        //wenn der Spieler die letzte Frage geantwortet hat, dann soll er noch die zuvor übersprungene Fragen antworten
        // es wird auch bemerkt, ob es um eine solche Frage handelt
        if (aktuelleFrageId === frageAnzahl){
            if (ubersprungenFragen.length == 0){
                // das Spiel gewonnen
                ausfuhrenBtn.style.visibility = "hidden";
                weiterBtn.style.visibility = "hidden";
                alert("Glückwunsch, du hast das Spiel gewonnen !!!");
            }
            else {
                // bemerken, dass ab jetzt die übersprungene Fragen geantwortet werden sollen
                istUbersprungenFrage = true;
                $('#mitteilungModal').modal('show').find(".modal-title").text("Mitteilung");
                $('#mitteilungModal').modal('show').find(".modal-body").text("Folgende sind die Fragen, die Sie übersprungen haben ");
                // die übersprungene Frage beantworten
                aktuelleFrageId = ubersprungenFragen[0];
                setViewForNextQuestion();
            }
        }
        // Spieler antwortet die nächste Frage in der Fragenliste
        else {
            aktuelleFrageId++;
            if (aktuelleFrageId === 2) // jetzt läuft die Zeit
            {
                Clock.start();
            }
            setViewForNextQuestion();
        }
    }


}


function setVisibilityWeiterBtn (){
    if (Object.values(listFragebObject[aktuelleFrageId-1])[3] == null) // antwort_id == 0, es ist keine Frage
    {
        weiterBtn.style.visibility = "visible";
        ausfuhrenBtn.style.visibility = "hidden";
    }
    else {
        weiterBtn.style.visibility = "hidden";
        ausfuhrenBtn.style.visibility = "visible";
    }
}

// die Spieler können bei ein paar fragen überspringen
function setVisibilityUberspringenBtn(){
    // die Fragen kann man nur einmal überspringen
    if (istUbersprungenFrage){
        uberspringenBtn.style.visibility = "hidden";
        return;
    }
    // Liste der Fragen, die Spieler überspringen kann
    const fragenZuUberspringen = [7,16,25,30,37,40,41,42];
    if (fragenZuUberspringen.includes(aktuelleFrageId)){
        uberspringenBtn.style.visibility = "visible";
    }
    else {
        uberspringenBtn.style.visibility = "hidden";
    }
}

function ausfuhren(){

    const code = sqlEditor.getValue();
    const feedbackArea = document.getElementById("ausgabe");


    if (code == "")
    {
        const div = createDivFeedback("Sie haben kein code eingegeben !", "#A43741");
        div.appendChild(document.createElement("br"));

        const letztesFeedback = feedbackArea.firstChild;
        feedbackArea.insertBefore(div, letztesFeedback);
        return;
    }
    // code vom spieler wird auf Sicherheit überprüft
    // der Spieler darf das Datenbankschema nicht ändern
    let codeLowercase = code.toLowerCase();
    if (codeLowercase.includes("create") || codeLowercase.includes("update")
        || codeLowercase.includes("delete") || codeLowercase.includes("drop")
        || codeLowercase.includes("table") || codeLowercase.includes("insert")){
        const div = createDivFeedback("keine Abfrage erkannt ", "#A43741");
        div.appendChild(document.createElement("br"));

        const letztesFeedback = feedbackArea.firstChild;
        feedbackArea.insertBefore(div, letztesFeedback);
        return;
    }
    // code vom Spieler wird zur bewertung an Server geschickt
    const dataToServer = {
        aktFragId: aktuelleFrageId,
        aktZeit: Clock.totalSeconds,
        istUbersprungenFrage: istUbersprungenFrage,
        level: level,
        spielerCode: code
    };

    $.ajax({
        type: "POST",
        url: "/sendCode",
        data: {
            spielerCodeData: JSON.stringify(dataToServer)
        },
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrf_header, csrf_token);
        },
        // response : bewertung,feedback, spaltenAnz, zeilenAnz,
        //          spaltenName1,spaltenName2, ..., data#data#data..., level, punkte, gewinn
        success: function (response) {
            // ein SQLException wurde geworfen
            if (response.SQLException != undefined){
                const div = createDivFeedback(response.feedback, "#A43741");
                div.appendChild(document.createElement("br"));

                const letztesFeedback = feedbackArea.firstChild;
                feedbackArea.insertBefore(div, letztesFeedback);
                return;
            }

            // daten aus der Server-Antwort zu holen
            let spaltenAnz = response.spaltenAnz;
            let zeilenAnz = response.zeilenAnz;
            let dataInString = response.data;

            // daten für Ausgabe-Tabelle
            let spaltenNamen = new Array();
            let daten = new Array();
            for (let i = 4; i< spaltenAnz +4; i++){
                const key = Object.keys(response)[i];
                const spaltenName = response[key];
                spaltenNamen.push(spaltenName);
            }
            daten = dataInString.split ("#");
            // tabelle für die Ausgabe erstellen
            let ausgabeTabelle = tabelleGenerator(spaltenAnz, zeilenAnz, spaltenNamen, daten);

            // spieler hat falsch geantwortet, wird das feedback rot gefärbt
            let labelColor = "";
            if (!response.bewertung){
                labelColor = "#A43741";
            }else labelColor = "#2D882D";
            // feedback ausgeben
            const div = createDivFeedback(response.feedback, labelColor);
            div.appendChild(ausgabeTabelle);
            div.appendChild(document.createElement("br"));

            // feedback und die Tabelle der Antwort ausgeben
            let letztesFeedback = feedbackArea.firstChild;
            feedbackArea.insertBefore(div, letztesFeedback);

            // spieler hat die Frage richtig geantwortet
            if (response.bewertung){
                weiterBtn.style.visibility = "visible";
                ausfuhrenBtn.style.visibility = "hidden";
                // update punkte
                punkte += Object.values(listFragebObject[aktuelleFrageId-1])[2];
                document.getElementById("punkte").innerText = punkte;

                // update womöglich level
                if (aktuelleFrageId == 22){ // 22
                    const tabelleListe = document.getElementById("tabelle-liste");
                    level = 2;
                    $('#mitteilungModal').modal('show').find('.modal-header').text("Glückwunsch");
                    $('#mitteilungModal').modal('show').find('.modal-body').text("Sie haben Level 2 erreicht !");
                    document.getElementById("level").innerText = level;
                    loadLevel2(tabelleListe);
                }

                if (aktuelleFrageId == 42){ // 42
                    const tabelleListe = document.getElementById("tabelle-liste");
                    level = 3;
                    $('#mitteilungModal').modal('show').find('.modal-header').text("Glückwunsch");
                    $('#mitteilungModal').modal('show').find('.modal-body').text("Sie haben Level 3 erreicht !");
                    document.getElementById("level").innerText = level;
                    loadLevel3(tabelleListe);
                }

                // update ranking
                document.getElementById("rankingNumber").innerText =response.ranking;
            }else {
                weiterBtn.style.visibility = "hidden";
                ausfuhrenBtn.style.visibility = "visible";
            }
        }
    });

    feedbackArea.scrollIntoView();
}

function setViewForNextQuestion() {
    frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1]; // nächste Frage text
    // tips zuklappen
    $('#tips').collapse('hide');
    tipsText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[4];
    setVisibilityWeiterBtn();
    setVisibilityUberspringenBtn();
    document.getElementById("navi").scrollIntoView();
}

// schickt die übersprungene Frage zu Server, um die Info in Datenbank zu speichern
function ueberspringen() {
    const ubersprungenFrage = {
        id : aktuelleFrageId
    };

    $.ajax({
        type: "POST",
        url: "/uberspringen",
        data: {
            ubersprungenFrageToServer: JSON.stringify(ubersprungenFrage)
        },
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrf_header, csrf_token);
        },
        success: function (response) {
            // speichere die übersprungene Frage in ihre Liste
            ubersprungenFragen.push(aktuelleFrageId);
            nachsteFrage();
        }
    });
}


function getLeaderboard() {
    $('#mitteilungModal').modal('show').find(".modal-title").text("Leaderboard");
    $('#mitteilungModal').modal('show').find(".modal-body").load("/getLeaderboard/leaderboard");
}

function getRanking() {
    $('#mitteilungModal').modal('show').find(".modal-title").text("Ranking");
    $('#mitteilungModal').modal('show').find(".modal-body").load("/getLeaderboard/rank");
}

function showSchema() {
    if (level === 1){
        $("#datenbankschemaImage").attr("src", "/images/level1-diagram.png");
        if (window.innerWidth >= 992){
            document.getElementById("datenbankschemaImage").style.width = "55%";
        }
        else {
            if (window.innerWidth >= 778){
                document.getElementById("datenbankschemaImage").style.width = "68%";
            }
            else {
                if (window.innerWidth >= 576){
                    document.getElementById("datenbankschemaImage").style.width = "85%";
                }
            }
        }
    }
    if (level === 2){
        $("#datenbankschemaImage").attr("src", "/images/level2-diagram.png");
    }
    if (level === 3){
        $("#datenbankschemaImage").attr("src", "/images/level3-diagram.png");
    }
    $('#datenbankschemaModal').modal('show');
}

function neustart(){
    $.ajax({
        type: "POST",
        url: "/neustart",
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrf_header, csrf_token);
        },
        success: function (response) {
            location.reload();
        }
    });
}

function test() {
    const obj = {
        val1: 1,
        val2: "frühling",
        val3: "schön"
    };

    $.ajax({
        type: "POST",
        url: "/test",
        data: {
            insel: JSON.stringify(obj)
        },
        beforeSend: function (xhr){
            xhr.setRequestHeader(csrf_header, csrf_token);
        },
        success: function (response) {
            console.log(response.hi + " ##1 " + response.ba +" ##2 " +response.bon);
        }
    });
}

