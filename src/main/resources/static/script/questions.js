
var i = 0;
var l = listFragen.length;

function nachsteFrage() {
    console.log("weiter clicked !!")
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
            success: function (response) {
                feedbackArea.innerHTML = response.feedback;
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