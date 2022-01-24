const listFragebObject = Object.values(listFragen);
const spielstandObject = Object.values(spielstand);

const frageAnzahl = listFragen.length;

const weiterBtn = document.getElementById("weiterBtn");
const ausfuhrenBtn = document.getElementById("ausfuehrenBtn");
const frageText = document.getElementById("frageText");
const spielInfos = document.getElementById("spielInfos");


let aktuelleFrageId = spielstandObject[2];
console.log(aktuelleFrageId);
frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1];

weiterBtnZustand();


function nachsteFrage() {
    if (aktuelleFrageId === frageAnzahl){
        // das Spiel gewonnen
        weiterBtn.disabled = true;
        return;
    }

    aktuelleFrageId++;
    if (aktuelleFrageId === 2) // jetzt läuft die Zeit
    {
        Clock.start();
    }

    frageText.innerText = Object.values(listFragebObject[aktuelleFrageId-1])[1]; // nächste Frage text
    weiterBtnZustand();
}

function weiterBtnZustand (){
    if (Object.values(listFragebObject[aktuelleFrageId-1])[3] == null) // antwort_id == 0, es ist keine Frage
    {
        weiterBtn.style.visibility = "visible";
    }
    else weiterBtn.style.visibility = "hidden";
}

function ausfuhren(){
    //const codeArea = document.getElementById("codeArea");
    //const code = codeArea.innerHTML;
    const code = sqlEditor.getValue();
    const feedbackArea = document.getElementById("ausgabe");


    if (code == "")
    {
        let div = document.createElement("div");
        let label = document.createElement("label");
        label.innerHTML = "du hast kein code eingegeben !";
        div.appendChild(label);
        div.appendChild(document.createElement("br"));

        let letztesFeedback = feedbackArea.firstChild;
        feedbackArea.insertBefore(div, letztesFeedback);
        console.log("kein code");
    }
    else
    {
        const dataToServer = {
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
                    const key = Object.keys(response)[i];
                    const spaltenName = response[key];
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
                    weiterBtn.style.visibility = "visible";
                    // update womöglich level in page-navigation
                    if (response.level != undefined){
                        document.getElementById("level").innerText = "Level: "+ response.level; // nächstes Level
                        alert("Glückwünsch ! Du hast Level "+ response.level + " erreicht !!");
                    }
                    // der Spieler hat gewonnen
                    if (response.gewinn != undefined){
                        ausfuhrenBtn.style.visibility = "hidden";
                        alert("Glückwunsch, du hast das Spiel gewonnen !!!");
                    }
                    // update punkte
                    document.getElementById("punkte").innerText = "Punkte: "+ response.punkte;
                }else weiterBtn.style.visibility = "hidden";
            }
        });
    }
    feedbackArea.scrollIntoView();
}

function tabelleGenerator (spaltenAnz, zeilenAnz, spaltenNamen, daten){
    const tabelle = document.createElement("table");

    const tabelleKopf = document.createElement("tr");
    for (let i = 0; i< spaltenAnz; i++){
        const th = document.createElement("th");
        th.appendChild(document.createTextNode(spaltenNamen[i]));
        tabelleKopf.appendChild(th);
    }
    tabelle.appendChild(tabelleKopf);

    for (let i = 0; i< zeilenAnz; i++){
        const tabelleZeile = document.createElement("tr");
        for (let j = 0; j< spaltenAnz; j++){
            const td = document.createElement("td");
            td.appendChild(document.createTextNode(daten[i*spaltenAnz + j]));
            tabelleZeile.appendChild(td);
        }
        tabelle.appendChild(tabelleZeile);
    }

    return tabelle;
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
        success: function (response) {
            var code = document.getElementById("codetext");
            code.innerText = response.hi + " ##1 " + response.ba +" ##2 " +response.bon ;
            console.log(response);
        }
    });
}