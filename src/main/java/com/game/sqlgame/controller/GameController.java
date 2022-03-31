package com.game.sqlgame.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.gameComponents.user_verwaltung.AktuellerSpieler;
import com.game.sqlgame.model.UbersprungenFragen;
import com.game.sqlgame.repository.FrageRepository;
import com.game.sqlgame.repository.SpielstandRepository;
import com.game.sqlgame.repository.UbersprungenFragenRepository;
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
import java.util.ArrayList;

@Controller
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private final JdbcTemplate jdbcTemplate;

    private final FrageRepository frageRepository;
    private final SpielstandRepository spielstandRepository;
    private final UbersprungenFragenRepository ubersprungenFragenRepository;

    private final ObjectMapper objectMapper;


    public GameController(JdbcTemplate jdbcTemplate, FrageRepository frageRepository,
                          SpielstandRepository spielstandRepository,
                          UbersprungenFragenRepository ubersprungenFragenRepository,
                          ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.frageRepository = frageRepository;
        this.spielstandRepository = spielstandRepository;
        this.ubersprungenFragenRepository = ubersprungenFragenRepository;
        this.objectMapper = objectMapper;

    }

    @GetMapping("/")
    public String start(Model model) {
        return "loginPage";
    }

    @GetMapping("/playing")
    public String login(Model model) {

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");

        Spielstand spielstand = spielstandRepository.findSpielstandByPlayerId(aktuellerSpieler.getId()).get();
        log.info(spielstand.toString());

        model.addAttribute("spielstand", spielstand);
        model.addAttribute("listFragen", frageRepository.findAllQuestions());
        model.addAttribute("spielerRanking", getRangAktuellerSpieler(aktuellerSpieler.getName()));
        model.addAttribute("ubersprungeneFragen", ubersprungenFragenRepository.getUberspringenFrageWithSpielerId(aktuellerSpieler.getId()));

        log.info(aktuellerSpieler.toString());
        return "playingPage";
    }

    @PostMapping(value = "/code-evaluation")
    public @ResponseBody
    ObjectNode codeBewertung(@RequestParam String spielerCodeData, Model model){

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");

        Spielstand spielstand = spielstandRepository.findSpielstandByPlayerId(aktuellerSpieler.getId()).get();

        log.info(spielerCodeData);
        ObjectNode objectNode = objectMapper.createObjectNode();

        JSONObject data = new JSONObject(spielerCodeData);

        String spieler_code = data.getString("spielerCode");

        int frageId = data.getInt("aktFragId");
        boolean istUbersprungenFrage = data.getBoolean("istUbersprungenFrage");
        int aktZeit = data.getInt("aktZeit");
        int level = data.getInt("level");

        if (level == 1 || frageId <= 22){
            spieler_code = "with bewohner as " +
                    "(select id, name, alter, hobby, beruf, beruf_erfahrung, status " +
                    "from bewohner " +
                    "where insel_id = 1) " + spieler_code;
        }
        log.info(spieler_code);

        Connection c1 = null;
        Statement s1 = null;
        Connection c2 = null;
        Statement s2 = null;

        try {
            c1 = jdbcTemplate.getDataSource().getConnection();
            s1 = c1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            c2 = jdbcTemplate.getDataSource().getConnection();
            s2 = c2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


            objectNode = checkQueryAnswer(spieler_code, frageId, s1,s2,c1,c2);
            // wenn die Antwort richtig ist
            if (objectNode.get("bewertung").asBoolean(true))
            {
                // wenn es um eine übersprungene Frage, wird es aus der Datenbank entfernt
                if(istUbersprungenFrage){
                    ubersprungenFragenRepository.deleteUbersprungenFrage(aktuellerSpieler.getId(), frageId);
                }
                // update punkte und zeit in der Datenbank
                int aktPunkte = spielstand.getPunkte();
                int neuPunkte = frageRepository.findQuestionById(frageId).get().getPunkte() + aktPunkte;
                spielstandRepository.updateSpielstand(aktuellerSpieler.getId(),level, neuPunkte, aktZeit);

                // update ranking vom Spieler
                objectNode.put("ranking", getRangAktuellerSpieler(aktuellerSpieler.getName()));
            }
            else
                return objectNode;
        }catch (SQLException e){
            objectNode.put("bewertung", false);
            objectNode.put("SQLException", true);
            String message = e.getMessage();
            // für die Fragen im Level 1, muss bei Fehlern richtige Position ausgegeben werden
            System.out.println(message.contains("Position") && level == 1);
            if (level == 1 && message.contains("Position")){
                String positionInString =  message.substring(message.lastIndexOf(" ")+1);
                int position = Integer.parseInt(positionInString);
                position -= 114;
                message = message.replace(positionInString, position+"");
                objectNode.put("feedback", message);
            }
            else
                objectNode.put("feedback" , e.getMessage());
        }
        finally {
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

    @GetMapping(value = "/{rank_or_leaderboard}")
    public String getLeaderboard(Model model, @PathVariable(value = "rank_or_leaderboard") String rank_or_leaderboard){

        ArrayList<ObjectNode> topSpieler = new ArrayList<>();
        // list von aktuelle Spieler und die Nachbarn in Ranking liste
        ArrayList<ObjectNode> aktSpielerUndSpielerDaneben = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        // Spieler nach ihrem Rang suchen
        String rang_sql = "select * " +
                "from (" +
                "select spieler.name, spielstand.punkte, spielstand.zeit, dense_rank() over w as rang " +
                "from spieler, spielstand " +
                "where spieler.id = spielstand.spieler_id " +
                "window w as (order by punkte desc, zeit) " +
                ") as tmp " +
                "where rang <= ? and rang >= ?";



        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            statement = connection.prepareStatement(rang_sql);


            if (rank_or_leaderboard.equals("leaderboard")){
                // Spieler mit dem Rang von 1 bis 5 ermitteln
                statement.setInt(1, 5);
                statement.setInt(2, 1);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    ObjectNode spieler = objectMapper.createObjectNode();
                    spieler.put("name", resultSet.getString("name"));
                    spieler.put("punkte", resultSet.getString("punkte"));
                    int seconds = resultSet.getInt("zeit");
                    String zeit = "" + seconds / 60 + ":" + seconds % 60;
                    spieler.put("zeit", zeit);
                    spieler.put("rang", resultSet.getString("rang"));
                    topSpieler.add(spieler);
                }
            }

            if (rank_or_leaderboard.equals("rank")){
                AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");
                int rang = getRangAktuellerSpieler(aktuellerSpieler.getName());
                if (rang != -1){
                    // Spieler mit 2 Rang besser  und 1 Rang schlechter als aktueller Spieler
                    statement.setInt(1, rang+1);
                    statement.setInt(2, rang-2);
                    ResultSet resultSet1 = statement.executeQuery();
                    while (resultSet1.next()){
                        ObjectNode spieler = objectMapper.createObjectNode();
                        spieler.put("name", resultSet1.getString("name"));
                        spieler.put("punkte", resultSet1.getString("punkte"));
                        int seconds = resultSet1.getInt("zeit");
                        String zeit = "" + seconds / 60 + ":" + seconds % 60;
                        spieler.put("zeit", zeit);
                        spieler.put("rang", resultSet1.getString("rang"));
                        aktSpielerUndSpielerDaneben.add(spieler);
                    }
                }
            }

        }catch (SQLException e){
            model.addAttribute("error", "die Top-Spieler nicht gefunden");
        }finally {
            try {
                closeConnection(statement, connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        model.addAttribute("aktSpielerUndSpielerDaneben", aktSpielerUndSpielerDaneben);
        model.addAttribute("topSpieler", topSpieler);
        return "leaderboard-ranking-modal";
    }

    @PostMapping("/question-skipping")
    @ResponseBody
    public void saveUbersprungenFrage(@RequestParam String ubersprungenFrageToServer, Model model){
        JSONObject jsonObject = new JSONObject(ubersprungenFrageToServer);
        int frageId = jsonObject.getInt("id");

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");
        ubersprungenFragenRepository.save(new UbersprungenFragen(aktuellerSpieler.getId(), frageId));

    }

    @PostMapping("/neustart")
    public String neustart (Model model){
        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");
        int aktuellerSpielerId = aktuellerSpieler.getId();

        spielstandRepository.updateSpielstand(aktuellerSpielerId,1,0,0,1);
        ubersprungenFragenRepository.getUberspringenFrageWithSpielerId(aktuellerSpielerId);

        Spielstand spielstand = spielstandRepository.findSpielstandByPlayerId(aktuellerSpielerId).get();

        model.addAttribute("spielstand", spielstand);
        model.addAttribute("listFragen", frageRepository.findAllQuestions());
        model.addAttribute("spielerRanking", getRangAktuellerSpieler(aktuellerSpieler.getName()));
        model.addAttribute("ubersprungeneFragen", ubersprungenFragenRepository.getUberspringenFrageWithSpielerId(aktuellerSpieler.getId()));
        System.out.println("loooooooogggggoooout");
        return "playingPage";
    }

    private ObjectNode checkQueryAnswer(String spieler_code, int frageId, Statement s1, Statement s2,
                                Connection c1, Connection c2) throws SQLException {


        String antwort = frageRepository.findQuestionById(frageId).get().getAntw().replace("\\\"", "'");
        ObjectNode objectNode = objectMapper.createObjectNode();


        ResultSet spieler_rst = s1.executeQuery(spieler_code);
        ResultSetMetaData spieler_rsmt = spieler_rst.getMetaData();


        ResultSet korrekt_rst = s2.executeQuery(antwort);
        ResultSetMetaData korrekt_rsmt = korrekt_rst.getMetaData();

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

    private int getRangAktuellerSpieler (String spielerName){
        int rang = -1;
        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "select rang " +
                "from (" +
                "select spieler.name, spielstand.punkte, spielstand.zeit, dense_rank() over w as rang " +
                "from spieler, spielstand " +
                "where spieler.id = spielstand.spieler_id " +
                "window w as (order by punkte desc, zeit) " +
                ") as tmp " +
                "where name = ?";

        rang = jdbcTemplate.queryForObject(sql, new Object[] {spielerName}, Integer.class);
        return rang;
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
