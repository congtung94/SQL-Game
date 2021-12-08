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
    var feedbackArea = document.getElementById("feedback");

    if (code == "")
    {
        feedbackArea.innerHTML = "du hast kein code eingegeben !";
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
            // response : bewertung,feedback, spaltenAnz, zeilenAnz,
            //          spaltenName1,spaltenName2, ..., data#data#data..., level, punkte, gewinn
            success: function (response) {
                // die Frage hat der Spieler richtig geantwortet oder das ist keine Frage
                if (Object.keys(response).length === 0){
                    return;
                }

                // syntax error, ein SQLException wurde geworfen
                if (Object.keys(response).length === 2){
                    feedbackArea.innerHTML = response.feedback +"<br><br>" +
                        feedbackArea.innerHTML;
                    return;
                }

                feedbackArea.innerHTML = response.feedback + "# spaltenAnz-"+ response.spaltenAnz
                    +" zeilenAnz-"+ response.zeilenAnz + " daten-" + response.data+ "<br><br>" +
                    feedbackArea.innerHTML;
                if (response.bewertung){
                    weiterBtn.disabled = false;
                    // update womöglich level in page-navigation
                    if (response.level != undefined){
                        document.getElementById("levelSpan").innerText = "Level: "+ response.level; // nächstes Level
                        alert("Glückwünsch ! Du hast Level "+ response.level + " erreicht !!");
                    }
                    // der hat gewonnen
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