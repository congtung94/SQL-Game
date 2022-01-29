
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

setVisibilityWeiterBtn();


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
    setVisibilityWeiterBtn();
    document.getElementById("navi").scrollIntoView();
}

function setVisibilityWeiterBtn (){
    if (Object.values(listFragebObject[aktuelleFrageId-1])[3] == null) // antwort_id == 0, es ist keine Frage
    {
        weiterBtn.style.visibility = "visible";
    }
    else weiterBtn.style.visibility = "hidden";
}

function ausfuhren(){

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
                        let newLevel = response.level;
                        document.getElementById("level").innerText = newLevel; // nächstes Level
                        alert("Glückwünsch ! Du hast Level "+ response.level + " erreicht !!");
                        const tabelleList = document.getElementById("tabelle-liste");
                        if (newLevel === 2){
                            loadLevel2(tabelleList);
                        }
                        if (newLevel === 3){
                            loadLevel3(tabelleList);
                        }
                    }
                    // der Spieler hat gewonnen
                    if (response.gewinn != undefined){
                        ausfuhrenBtn.style.visibility = "hidden";
                        alert("Glückwunsch, du hast das Spiel gewonnen !!!");
                    }
                    // update punkte
                    document.getElementById("punkte").innerText =response.punkte;
                }else weiterBtn.style.visibility = "hidden";
            }
        });
    }
    feedbackArea.scrollIntoView();
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

