package com.game.sqlgame.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.game_components.user_verwaltung.AktuellerSpieler;
import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.game_components.user_verwaltung.SpielerRegistrierenForm;
import com.game.sqlgame.game_components.user_verwaltung.SpielerRegistrierenValidator;
import com.game.sqlgame.service.AntwortService;
import com.game.sqlgame.service.FrageService;
import com.game.sqlgame.service.SpielerService;
import com.game.sqlgame.service.SpielstandService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final JdbcTemplate jdbcTemplate;

    private final SpielerService spielerService;
    private final FrageService frageService;
    private final SpielstandService spielstandService;
    private final AntwortService antwortService;
    private final ObjectMapper objectMapper;
    private final SpielerRegistrierenValidator validator;

    public MainController(JdbcTemplate jdbcTemplate, SpielerService spielerService,
                          FrageService frageService, SpielstandService spielstandService,
                          AntwortService antwortService, ObjectMapper objectMapper, SpielerRegistrierenValidator validator) {
        this.jdbcTemplate = jdbcTemplate;
        this.spielerService = spielerService;
        this.frageService = frageService;
        this.spielstandService = spielstandService;
        this.antwortService = antwortService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @InitBinder("registrierenForm")
    public void initBinder (WebDataBinder webDataBinder){
        webDataBinder.addValidators(validator);
    }

    @GetMapping("/")
    public String start(Model model) {

        return "start";
    }

    @GetMapping("/registrieren")
    public String registrieren (){
        return "registrieren";
    }

    @PostMapping("/registrieren")
    public String registrieren (@Valid @ModelAttribute("registrierenForm")SpielerRegistrierenForm form,
                                BindingResult bindingResult, Model model, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            log.info(bindingResult.getGlobalError().getDefaultMessage());
            return "registrieren";
        }
        Spieler spieler = new Spieler();
        spieler.setName(form.getName());
        spieler.setPasswort(form.getPasswort());
        spielerService.save(spieler);

        Spielstand spielstand = new Spielstand();
        spielstand.setSpielerId(spielerService.getPlayerByName(spieler.getName()).get().getId());
        spielstandService.save(spielstand);

        authWithHttpServletRequest(request, spieler.getName(), spieler.getPasswort());

        return "redirect:/spielen";
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error("Error while login ", e);
        }
    }

    @GetMapping("/spielen")
    public String login(Model model) {

        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");

        /*try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            Connection c = jdbcTemplate.getDataSource().getConnection();
            Statement s = c.createStatement();

            ResultSet resultSet1 = s.executeQuery("select * from bestellung_details");
            ResultSetMetaData resultSetMetaData1 = resultSet1.getMetaData();
            ResultSet resultSet2 = statement.executeQuery("select * from spieler");
            ResultSetMetaData resultSetMetaData2 = resultSet2.getMetaData();

            log.info("float = "+ resultSetMetaData1.getColumnType(3));
            log.info("int = " + resultSetMetaData2.getColumnType(1));
            log.info("string = " + resultSetMetaData2.getColumnType(2));
        }catch (SQLException e){
            log.info(e.getMessage());
        }

        boolean test =
                checkQueryAnswer("select bew.name as kauefer_name,i.name as Insel_name , bst.id as bestellung_id\n" +
                                "from bestellung bst, bewohner bew, insel i\n" +
                                "where bst.kaeufer_id = bew.id and bew.ort_id = i.id and i.name = 'frühling' and\n" +
                                "(bst.verkaeufer_id  in (select bewohner.id from bewohner, insel where bewohner.ort_id = insel.id and insel.name = 'sommer'))",
                        6);

        log.info("korrekt oder nicht  ###### " + test);*/

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktuellerSpieler.getId()).get();
        log.info(spielstand.toString());

        int aktuelleFrageId = spielstand.getAktuelleFrageId();
        log.info("aktuelle Frage = " + spielstand.getAktuelleFrageId());
        if (aktuelleFrageId == 0)
        {
            model.addAttribute("aktuelleFrage", 0);

        }
        else
        {
            log.info("line 121   "+frageService.findQuestionById(aktuelleFrageId).get().getText());
        }

        model.addAttribute("spielstand", spielstand);
        model.addAttribute("listFragen", frageService.findAllQuestions());

        log.info(aktuellerSpieler.toString());
        return "level1";
    }

    @Transactional
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
        if (frageId == spielstand.getAktuelleFrageId()-1 || frageService.questionWithoutAnswer(frageId)){
            return objectNode;
        }

        //boolean check = checkQueryAnswer(spieler_code,frageId);
        try {
            objectNode = checkQueryAnswer(spieler_code, frageId);
            // wenn die Antwort richtig ist
            if (objectNode.get("bewertung").asBoolean(true))
            {
                // war das die letzte Frage ?
                if (frageId == frageService.countFrage()){
                    int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getMax_punkte();
                    log.info("maximal punkte: " + neuPunkte);
                    spielstandService.updateSpielstand(spielstand.getSpielStandId(), neuPunkte);
                    objectNode.put("punkte", neuPunkte);
                    objectNode.put("gewinn", "Glückwunsch, du hast gewonnen"+ "\n deine Punkte: " + neuPunkte);
                    return objectNode;
                }
                // update level, punkte, aktuelle FrageId in spielstand
                int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getMax_punkte();
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
        }

        /*boolean check = true;
        objectNode.put("bewertung", check);
        if (check){
            // der Spieler hat die letzte frage richtig geanwortet
            if (frageId == frageService.countFrage()){

                int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getMax_punkte();
                log.info("maximal punkte: " + neuPunkte);
                spielstandService.updateSpielstand(spielstand.getSpielStandId(), neuPunkte);
                objectNode.put("punkte", neuPunkte);
                objectNode.put("feedback", "Glückwunsch, du hast gewonnen"+ "\n deine Punkte: " + neuPunkte);
                return objectNode;
            }


            int neuPunkte = spielstand.getPunkte() + frageService.findQuestionById(frageId).get().getMax_punkte();
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
            objectNode.put("feedback",
                    "glückwunsch, deine Antwort ist richtig, " +
                            " deine Punkte : " + neuPunkte);
        }
        else objectNode.put("feedback", "dein antwort ist leider nicht richtig");

        return objectNode;*/
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

    @Transactional
    ObjectNode checkQueryAnswer(String spieler_antwort, int frageId) throws SQLException {

        int antwortId = frageService.findAnswerIdByQuestionId(frageId);
        Antwort antwort = antwortService.findAnswerById(antwortId).get();
        String antwort_text = antwort.getSQL().replace("\\\"", "'");
        ObjectNode objectNode = objectMapper.createObjectNode();

        /*if (antwort.getAntwortTyp() == 1) // zahl
        {
            try {
                int spieler_count = jdbcTemplate.queryForObject(spieler_antwort, Integer.class);
                int count = jdbcTemplate.queryForObject(antwort_text, Integer.class);
                if (count == spieler_count)
                    return true;
                else {
                    log.info("count stimmt nicht antwort = "+ spieler_count +"spieler_cout = "+ count);
                    return false;
                }
            }catch (Exception e){
                log.info("line 187 mainController # " + e.getMessage()
                );
                return false;
            }

        }*/
        /*else*/  // resultset

        Connection c1 = jdbcTemplate.getDataSource().getConnection();
        Statement s1 = c1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet spieler_rst = s1.executeQuery(spieler_antwort);
        ResultSetMetaData spieler_rsmt = spieler_rst.getMetaData();

        Connection c2 = jdbcTemplate.getDataSource().getConnection();
        Statement s2 = c2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet korrekt_rst = s2.executeQuery(antwort_text);
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
                if (colTyp == -5){ // bigInt für count
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
                if (colTyp == 91)
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
    @Transactional
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
                    data.append(resultSet.getFloat(i));
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
        // das letzte # entfernen
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
