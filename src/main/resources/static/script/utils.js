/* Tabelle für die Ausgabe der Anfrage erestellen */
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

/* datenbankschema nach level anzeigen */
function loadLevel2 (databaseshema){
    let lastTable = databaseshema.lastChild;
    let newTable = createTableDetails("insel", ["id", "koord_id","name", "abstand"]);
    databaseshema.insertBefore(newTable, lastTable);

    lastTable = databaseshema.lastChild;
    newTable = createTableDetails("koordinaten",
        ["id", "breite_grad","breite_richtung", "laenge_grad", "laenge_richtung"]);
    databaseshema.insertBefore(newTable, lastTable);

    lastTable = databaseshema.lastChild;
    newTable = createTableDetails("wetter",
        ["koord_id", "zeit","feuchtigkeit", "wind_geschw"]);
    databaseshema.insertBefore(newTable, lastTable);

}

function loadLevel3 (schema){
    let lastTable = schema.lastChild;
    let newTable = createTableDetails("hersteller", ["id", "name","bekanntheitsgrad"]);
    schema.insertBefore(newTable, lastTable);

    lastTable = schema.lastChild;
    newTable = createTableDetails("ware",
        ["id", "hersteller_id","name", "preis"]);
    schema.insertBefore(newTable, lastTable);

    lastTable = schema.lastChild;
    newTable = createTableDetails("kaeufe",
        ["id", "ware_id","anzahl", "tag"]);
    schema.insertBefore(newTable, lastTable);
}

function createTableDetails (tableName, attributtes){
    const newTable = document.createElement("details");

    /*create summary*/
    const sum = document.createElement("summary");
    sum.appendChild(document.createTextNode(tableName));

    /*create details*/
    const listAttributes = document.createElement("ul");
    let countAttributes = attributtes.length;
    for (let i = 0; i< countAttributes; i++){
        let ele_tmp  = document.createElement("li");
        ele_tmp.appendChild(document.createTextNode(attributtes[i]));
        listAttributes.appendChild(ele_tmp);
    }

    newTable.appendChild(sum);
    newTable.appendChild(listAttributes);

    return newTable;
}

/* datenbankschema nach window-größe anpassen (zeigen oder verstecken)  */

window.addEventListener('resize', function (event) {
    if (window.innerWidth < 768){
        document.getElementById("datenbankschema").classList.remove("show");
    }
    else {
        document.getElementById("datenbankschema").classList.add("show");
    }
});