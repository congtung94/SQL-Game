package com.game.sqlgame.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.gameComponents.user_verwaltung.AktuellerSpieler;
import com.game.sqlgame.service.FrageService;
import com.game.sqlgame.service.SpielerService;
import com.game.sqlgame.service.SpielstandService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private final JdbcTemplate jdbcTemplate;

    private final SpielerService spielerService;
    private final FrageService frageService;
    private final SpielstandService spielstandService;

    private final ObjectMapper objectMapper;


    public GameController(JdbcTemplate jdbcTemplate, SpielerService spielerService,
                          FrageService frageService, SpielstandService spielstandService,
                           ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.spielerService = spielerService;
        this.frageService = frageService;
        this.spielstandService = spielstandService;
        this.objectMapper = objectMapper;

    }

    @GetMapping("/")
    public String start(Model model) {
        return "start";
    }

    @GetMapping("/spielen")
    public String login(Model model) {

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktuellerSpieler.getId()).get();
        log.info(spielstand.toString());

        model.addAttribute("spielstand", spielstand);
        model.addAttribute("listFragen", frageService.findAllQuestions());

        log.info(aktuellerSpieler.toString());
        return "main";
    }

    @PostMapping(value = "/sendCode")
    public @ResponseBody
    ObjectNode codeBewertung(@RequestParam String spielerCodeData, Model model){

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktuellerSpieler.getId()).get();

        log.info(spielerCodeData);
        ObjectNode objectNode = objectMapper.createObjectNode();

        JSONObject data = new JSONObject(spielerCodeData);

        String spieler_code = data.getString("spielerCode");
        log.info(spieler_code);
        int frageId = data.getInt("aktFragId");

        // die Frage hat der Spieler richtig geantwortet oder das ist keine Frage
        // ein leeres ObjectNode wird zurückgegeben
        if (frageId == spielstand.getAktuelleFrageId()-1 || frageService.findQuestionById(frageId).get().getAntw() == null){
            return objectNode;
        }

        Connection c1 = null;
        Statement s1 = null;
        Connection c2 = null;
        Statement s2 = null;

        //boolean check = checkQueryAnswer(spieler_code,frageId);
        try {
            c1 = jdbcTemplate.getDataSource().getConnection();
            s1 = c1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            c2 = jdbcTemplate.getDataSource().getConnection();
            s2 = c2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


            objectNode = checkQueryAnswer(spieler_code, frageId, s1,s2,c1,c2);
            // wenn die Antwort richtig ist
            if (objectNode.get("bewertung").asBoolean(true))
            {
                // war das die letzte Frage ?
                if (frageId == frageService.countFrage()){
                    int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getPunkte();
                    log.info("maximal punkte: " + neuPunkte);
                    spielstandService.updateSpielstand(spielstand.getSpielStandId(), neuPunkte);
                    objectNode.put("punkte", neuPunkte);
                    objectNode.put("gewinn", "Glückwunsch, du hast gewonnen"+ "\n deine Punkte: " + neuPunkte);
                    return objectNode;
                }
                // update level, punkte, aktuelle FrageId in spielstand
                int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getPunkte();
                log.info("neunPunkte: " + neuPunkte);
                if (frageId == 3){
                    spielstandService.updateSpielstand(spielstand.getSpielStandId(),2, neuPunkte,frageId+1);
                    objectNode.put("level", 2);
                }
                if (frageId == 5){
                    spielstandService.updateSpielstand(spielstand.getSpielStandId(),3, neuPunkte,frageId+1);
                    objectNode.put("level", 3);
                }
                spielstandService.updateSpielstand(spielstand.getSpielStandId(), neuPunkte,frageId+1);
                objectNode.put("punkte",neuPunkte);
            }
            else
                return objectNode;
        }catch (SQLException e){
            objectNode.put("bewertung", false);
            objectNode.put("feedback" , e.getMessage());
        }finally {
            if (c1 != null) {
                try {
                    closeConnection(s1,c1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (c2 != null) {
                try {
                    closeConnection(s2,c2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return objectNode;
    }

    @PostMapping(value = "/test")
    public @ResponseBody
    ObjectNode saysomething(@RequestParam String insel){

        log.info(insel);

        JSONObject jsonObject = new JSONObject(insel);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("hi", jsonObject.getInt("val1"));
        objectNode.put("ba", jsonObject.getString("val2"));
        objectNode.put("bon", jsonObject.getString("val3"));
        log.info(insel.toString());
        return objectNode;
    }

    @PostMapping("/neustart")
    public String neustart (Model model){
        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");
        int aktuellerSpielerId = aktuellerSpieler.getId();

        spielstandService.updateSpielstadNeustart(aktuellerSpielerId,1,0,0,1);

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktuellerSpielerId).get();

        model.addAttribute("spielstand", spielstand);
        model.addAttribute("listFragen", frageService.findAllQuestions());

        return "main";
    }

    ObjectNode checkQueryAnswer(String spieler_antwort, int frageId, Statement s1, Statement s2,
                                Connection c1, Connection c2) throws SQLException {


        String antwort = frageService.findQuestionById(frageId).get().getAntw().replace("\\\"", "'");
        ObjectNode objectNode = objectMapper.createObjectNode();


        ResultSet spieler_rst = s1.executeQuery(spieler_antwort);
        ResultSetMetaData spieler_rsmt = spieler_rst.getMetaData();


        ResultSet korrekt_rst = s2.executeQuery(antwort);
        ResultSetMetaData korrekt_rsmt = korrekt_rst.getMetaData();

        log.info("line 278 anzahl an spalten "+ spieler_rsmt.getColumnCount() +
                ", type " + spieler_rsmt.getColumnType(1));


        // objNode : "bewertung","feedback", spaltenAnz", "zeilenAnz",
        // "spaltenName1" , "spaltenName2", ..., "data#data#data..."

        int colNr = korrekt_rsmt.getColumnCount();
        if (colNr > spieler_rsmt.getColumnCount()){
            log.info("hier ist falsch : zu wenig spalten");
            objectNode.put("bewertung", false);
            objectNode.put("feedback", "deine Antwort hat zu wenig Spalten");
            resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
            closeConnection(s1,c1);
            closeConnection(s2,c2);
            return objectNode;
        }
        if (colNr < spieler_rsmt.getColumnCount()){
            log.info("hier ist falsch : zu viel spalten");
            objectNode.put("bewertung", false);
            objectNode.put("feedback", "deine Antwort hat zu viel Spalten");
            resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
            closeConnection(s1,c1);
            closeConnection(s2,c2);
            return objectNode;
        }
        // bisher, die anzahl an spalten stimmt
        int aktuelleZeile = 0;
        // Zeile für zeile vergleichen
        while (korrekt_rst.next())
        {
            aktuelleZeile++;
            log.info("aktuelle zeile " + aktuelleZeile);
            if (!spieler_rst.next()) // spieler_rst hat zu wenig zeilen
            {
                log.info("hier ist falsch: spieler_rst hat zu wenig zeilen" );
                objectNode.put("bewertung", false);
                objectNode.put("feedback", "deine Antwort hat zu wenig Zeilen");
                resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                closeConnection(s1,c1);
                closeConnection(s2,c2);
                return objectNode;
            }
            for (int col = 1; col<=colNr;col++)
            {
                int colTyp = korrekt_rsmt.getColumnType(col);
                log.info("coltyp = "+ colTyp);
                if (spieler_rsmt.getColumnType(col) != colTyp) // spalten typ nicht gleich
                {
                    log.info("hier ist falsch :spalten typ nicht gleich : col " + col + "zeile: " +aktuelleZeile);
                    objectNode.put("bewertung", false);
                    objectNode.put("feedback", col +".Spalte hat falschen Datentyp");
                    resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                    closeConnection(s1,c1);
                    closeConnection(s2,c2);
                    return objectNode;
                }
                if (colTyp == -5){ // bigInt wird zurückgegeben, wenn count durchgeführt wird
                    if (spieler_rst.getLong(col) != korrekt_rst.getLong(col))
                    {
                        log.info("hier ist falsch: der Typ ist BigInt : col " + col + "zeile: " +aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                    else continue;
                }
                if (colTyp == 4) // der Typ ist Integer
                {
                    if (spieler_rst.getInt(col) != korrekt_rst.getInt(col))
                    {
                        log.info("hier ist falsch: der Typ ist Integer : col " + col + "zeile: " +aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen integer Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                    else continue;
                }

                if (colTyp == 8) // der Typ ist float
                {
                    if (spieler_rst.getInt(col) != korrekt_rst.getInt(col))
                    {
                        log.info("hier ist falsch : der Typ ist float : col " + col + "zeile: " +aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen float Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                    else continue;
                }
                if (colTyp == 2) // der Typ ist numeric (double)
                {
                    if (spieler_rst.getDouble(col) != korrekt_rst.getDouble(col))
                    {
                        log.info("hier ist falsch : der Typ ist double : col " + col + "zeile: " +aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen double Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                    else continue;
                }
                if (colTyp == 91) // der Typ ist Date
                {
                    if (!spieler_rst.getDate(col).equals(korrekt_rst.getDate(col)))
                    {
                        log.info("hier ist falsch : der typ ist Date : col "+ col + ", zeile "+ aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen Date Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                }
                if (colTyp == 12) // der Typ ist String
                {
                    if (!spieler_rst.getString(col).equals(korrekt_rst.getString(col)))
                    {
                        log.info("hier ist falsch : der Typ ist String : col " + col + "zeile: " +aktuelleZeile);
                        objectNode.put("bewertung", false);
                        objectNode.put("feedback", col+".Spalte, " + aktuelleZeile+".Zeile"
                                +" hat falschen string Wert");
                        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
                        closeConnection(s1,c1);
                        closeConnection(s2,c2);
                        return objectNode;
                    }
                    else continue;
                }

            }
        }

        if (spieler_rst.next()){
            objectNode.put("bewertung", false);
            objectNode.put("feedback", "deine Antwort hat zu viel zeilen");
            resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);
            closeConnection(s1,c1);
            closeConnection(s2,c2);
            return objectNode;
        }

        objectNode.put("bewertung", true);
        objectNode.put("feedback", "deine Antwort ist richtig");
        resultSetToObjectNode(objectNode, spieler_rst, spieler_rsmt);

        closeConnection(s1,c1);
        closeConnection(s2,c2);
        return objectNode;
    }


    // objectNode : "spaltenAnz", "zeilenAnz", "spaltenName1" , "spaltenName2", ..., "data#data#data..."
    void resultSetToObjectNode(ObjectNode objectNode, ResultSet resultSet,
                               ResultSetMetaData resultSetMetaData) throws SQLException {
        // Anzahl an Spalten
        int spaltenAnz = resultSetMetaData.getColumnCount();
        objectNode.put("spaltenAnz", spaltenAnz);
        log.info("spalten ANzahl line 391 " + spaltenAnz + " ,spalten name = " + resultSetMetaData.getColumnName(1));

        int zeilenAnz = 0;
        // Zeile für zeile in objectNode übertragen
        StringBuilder data = new StringBuilder();

        resultSet.beforeFirst();
        while (resultSet.next()){
            zeilenAnz++;
            for (int i = 1; i<= spaltenAnz;i++){
                int colTyp = resultSetMetaData.getColumnType(i);
                if (colTyp == 4 || colTyp == -5){
                    data.append(resultSet.getInt(i));
                    data.append("#");
                }
                if (colTyp == 8){
                    data.append(String.format("%.2f", resultSet.getFloat(i)));
                    log.info("String.format " + String.format("%.2f", resultSet.getFloat(i)));
                    data.append("#");
                }
                if (colTyp == 2){
                    data.append(String.format("%.2f", resultSet.getDouble(i)));
                    data.append("#");
                }
                if (colTyp == 91){
                    data.append(convertSQLDateToUtilDate(resultSet.getDate(i)));
                    data.append("#");
                }
                if (colTyp == 12){
                    data.append(resultSet.getString(i));
                    data.append("#");
                }

            }
        }
        log.info("zeilen anzahl = " + zeilenAnz);

        // das letzte # entfernen
        if (data.length() != 0)
            data.deleteCharAt(data.length()-1);
        // Anzahl an Zeilen
        objectNode.put("zeilenAnz", zeilenAnz);
        // Spaltennamen in objectNode übertragen
        for (int i = 1; i <= spaltenAnz; i++){
            objectNode.put("spaltenName" +i, resultSetMetaData.getColumnName(i));
        }
        // data an objectNode übertragen
        objectNode.put("data", data.toString());
    }

    private void closeConnection (Statement statement, Connection connection) throws SQLException {
        if (!statement.isClosed()){
            statement.close();
        }
        if (!connection.isClosed()){
            connection.close();
        }
    }

    private String convertSQLDateToUtilDate (java.sql.Date sqlDate){
        java.util.Date date = new java.util.Date(sqlDate.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
