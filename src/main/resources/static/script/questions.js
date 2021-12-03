
var i = 0;
var l = listFragenText.length;

function nachsteFrage() {
    if (i < l){
        document.getElementById("thetext").innerHTML = listFragenText[i] + "\n";
        i++;
    }
}