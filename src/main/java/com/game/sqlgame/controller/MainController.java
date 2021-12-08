package com.game.sqlgame.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.game_components.user_verwaltung.Spieler;
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
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final JdbcTemplate jdbcTemplate;

    private final SpielerService spielerService;
    private final FrageService frageService;
    private final SpielstandService spielstandService;
    private final AntwortService antwortService;
    private final Spieler aktivSpieler;
    private final ObjectMapper objectMapper;

    public MainController(JdbcTemplate jdbcTemplate, SpielerService spielerService,
                          FrageService frageService, SpielstandService spielstandService,
                          AntwortService antwortService, Spieler aktivSpieler, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.spielerService = spielerService;
        this.frageService = frageService;
        this.spielstandService = spielstandService;
        this.antwortService = antwortService;
        this.aktivSpieler = aktivSpieler;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("spieler", new Spieler());
        return "start";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("spieler") Spieler spieler, Model model) {
        // check Login-Daten
        if (!spielerService.existsByName(spieler.getName())){
            model.addAttribute("error", "der Username existiert nicht");
            return "start";
        }
        if (!spieler.getPasswort().equals(spielerService.getPlayerByName(spieler.getName()).get().getPasswort())){
            model.addAttribute("error", "falsche Passwort");
            return "start";
        }

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

        spieler = spielerService.getPlayerByName(spieler.getName()).get();

        // aktiver Spieler
        int id = spieler.getId();
        String name = spieler.getName();
        aktivSpieler.setId(id);
        aktivSpieler.setName(name);

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(id).get();
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

        log.info(spieler.toString());
        return "level1";
    }

    @PostMapping(value = "/sendCode")
    public @ResponseBody
    ObjectNode codeBewertung(@RequestParam String spielerCodeData){

        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktivSpieler.getId()).get();

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

        boolean check = checkQueryAnswer(spieler_code,frageId);



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
    boolean checkQueryAnswer(String spieler_antwort, int frageId){

        int antwortId = frageService.findAnswerIdByQuestionId(frageId);
        Antwort antwort = antwortService.findAnswerById(antwortId).get();
        String antwort_text = antwort.getSQL().replace("\\\"", "'");


        if (antwort.getAntwortTyp() == 1) // zahl
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

        }

        else  // resultset
        {
            try
            {
                Connection c1 = jdbcTemplate.getDataSource().getConnection();
                Statement s1 = c1.createStatement();
                ResultSet spieler_rst = s1.executeQuery(spieler_antwort);
                ResultSetMetaData spieler_rsmt = spieler_rst.getMetaData();

                Connection c2 = jdbcTemplate.getDataSource().getConnection();
                Statement s2 = c2.createStatement();
                ResultSet korrekt_rst = s2.executeQuery(antwort_text);
                ResultSetMetaData korrekt_rsmt = korrekt_rst.getMetaData();


                int colNr = korrekt_rsmt.getColumnCount();
                if (colNr != spieler_rsmt.getColumnCount()){
                    log.info("hier ist falsch : zu wenig spalten");
                    return false;
                }
                else {
                    int aktuelleZeile = 0;
                    // Zeile für zeile vergleichen
                    while (korrekt_rst.next()){
                        aktuelleZeile++;
                        if (!spieler_rst.next()) // spieler_rst hat zu wenig zeilen
                        {
                            log.info("hier ist falsch: spieler_rst hat zu wenig zeilen" );
                            return false;
                        }
                        for (int col = 1; col<colNr;col++){
                            int colTyp = korrekt_rsmt.getColumnType(col);
                            if (spieler_rsmt.getColumnType(col) != colTyp) // spalten typ nicht gleich
                            {
                                log.info("hier ist falsch :spalten typ nicht gleich : col " + col + "zeile: " +aktuelleZeile);
                                return false;
                            }
                            if (colTyp == 4) // der Typ ist Integer
                            {
                                if (spieler_rst.getInt(col) != korrekt_rst.getInt(col))
                                {
                                    log.info("hier ist falsch: der Typ ist Integer : col " + col + "zeile: " +aktuelleZeile);
                                    return false;
                                }
                                else continue;
                            }
                            if (colTyp == 8) // der Typ ist float
                            {
                                if (spieler_rst.getInt(col) != korrekt_rst.getInt(col))
                                {
                                    log.info("hier ist falsch : der Typ ist float : col " + col + "zeile: " +aktuelleZeile);
                                    return false;
                                }
                                else continue;
                            }
                            if (colTyp == 12) // der Typ ist String
                            {
                                if (!spieler_rst.getString(col).equals(korrekt_rst.getString(col)))
                                {
                                    log.info("hier ist falsch : der Typ ist String : col " + col + "zeile: " +aktuelleZeile);
                                    return false;
                                }
                                else continue;
                            }
                        }
                    }
                }
                s1.close();
                s2.close();
                c1.close();
                c2.close();
            }catch (Exception e)
            {
                log.info("ein SQL exception #####   "+ e.getMessage());
                return false;
            }

        }
        return true;
    }


}
