let listFragebObject = Object.values(listFragen);
let spielstandObject = Object.values(spielstand);

let frageAnzahl = listFragen.length;

let weiterBtn = document.getElementById("weiterBtn");
let ausfuhrenBtn = document.getElementById("ausfuehrenBtn");
let frageText = document.getElementById("frageText");

var aktuelleFrageId = spielstandObject[5];
console.log(aktuelleFrageId);
frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1];

if (Object.values(listFragebObject[aktuelleFrageId-1])[4] == 0) // antwort_id == 0
{
    weiterBtn.disabled = false;
}
else weiterBtn.disabled = true;

function nachsteFrage() {
    if (aktuelleFrageId == frageAnzahl){
        // das Spiel gewonnen
        weiterBtn.disabled = true;
        return;
    }
    aktuelleFrageId++;
    frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1]; // nächste Frage text
    if (Object.values(listFragebObject[aktuelleFrageId-1])[4] == 0) // antwort_id == 0
    {
        weiterBtn.disabled = false;
    }
    else weiterBtn.disabled = true;
}


function ausfuhren(){
    var codeArea = document.getElementById("codeArea");
    var code = codeArea.value;
    var feedbackArea = document.getElementById("ausgabe");

    if (code == "")
    {
        let div = document.createElement("div");
        let label = document.createElement("label");
        label.innerHTML = "du hast kein code eingegen !";
        div.appendChild(label);
        div.appendChild(document.createElement("br"));

        let letztesFeedback = feedbackArea.firstChild;
        feedbackArea.insertBefore(div, letztesFeedback);
        console.log("kein code");
    }
    else
    {
        var dataToServer = {
            aktFragId: aktuelleFrageId,
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
                // die Frage hat der Spieler richtig geantwortet oder das ist keine Frage
                if (Object.keys(response).length === 0){
                    return;
                }

                // syntax error, ein SQLException wurde geworfen
                if (Object.keys(response).length === 2){
                    let div = document.createElement("div");
                    let label = document.createElement("label");
                    label.innerHTML = response.feedback;
                    div.appendChild(label);
                    div.appendChild(document.createElement("br"));

                    let letztesFeedback = feedbackArea.firstChild;
                    feedbackArea.insertBefore(div, letztesFeedback);

                    return;
                }
                let spaltenAnz = response.spaltenAnz;
                let zeilenAnz = response.zeilenAnz;
                let dataInString = response.data;

                // daten für Ausgabe-Tabelle
                let spaltenNamen = new Array();
                let daten = new Array();
                for (let i = 4; i< spaltenAnz +4; i++){
                    var key = Object.keys(response)[i];
                    var spaltenName = response[key];
                    spaltenNamen.push(spaltenName);
                }
                daten = dataInString.split ("#");
                console.log(daten);
                let ausgabeTabelle = tabelleGenerator(spaltenAnz, zeilenAnz, spaltenNamen, daten);


                let div = document.createElement("div");
                let label = document.createElement("label");
                label.innerHTML = response.feedback;
                div.appendChild(label);
                div.appendChild(ausgabeTabelle);
                div.appendChild(document.createElement("br"));

                let letztesFeedback = feedbackArea.firstChild;
                feedbackArea.insertBefore(div, letztesFeedback);

                if (response.bewertung){
                    weiterBtn.disabled = false;
                    // update womöglich level in page-navigation
                    if (response.level != undefined){
                        document.getElementById("levelSpan").innerText = "Level: "+ response.level; // nächstes Level
                        alert("Glückwünsch ! Du hast Level "+ response.level + " erreicht !!");
                    }
                    // der Spieler hat gewonnen
                    if (response.gewinn != undefined){
                        ausfuhrenBtn.disabled = true;
                        alert("Glückwunsch, du hast das Spiel gewonnen !!!");
                    }
                    // update punkte
                    document.getElementById("punkteSpan").innerText = "Punkte: " + response.punkte;
                }else weiterBtn.disabled = true;
            }
        });
    }
}

function tabelleGenerator (spaltenAnz, zeilenAnz, spaltenNamen, daten){
    var tabelle = document.createElement("table");
    tabelle.border =1;
    tabelle.style.borderCollapse = "collapse";
    var tabelleKopf = document.createElement("tr");
    for (let i = 0; i< spaltenAnz; i++){
        var th = document.createElement("th");
        th.appendChild(document.createTextNode(spaltenNamen[i]));
        tabelleKopf.appendChild(th);
    }
    tabelle.appendChild(tabelleKopf);

    for (let i = 0; i< zeilenAnz; i++){
        var tabelleZeile = document.createElement("tr");
        for (let j = 0; j< spaltenAnz; j++){
            var td = document.createElement("td");
            td.appendChild(document.createTextNode(daten[i*spaltenAnz + j]));
            tabelleZeile.appendChild(td);
        }
        tabelle.appendChild(tabelleZeile);
    }

    return tabelle;
}


function test() {
    var obj = {
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
        success: function (response) {
            var code = document.getElementById("codetext");
            code.innerText = response.hi + " ##1 " + response.ba +" ##2 " +response.bon ;
            console.log(response);
        }
    });
}