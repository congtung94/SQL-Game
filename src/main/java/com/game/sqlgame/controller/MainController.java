package com.game.sqlgame.controller;


import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.service.AntwortService;
import com.game.sqlgame.service.FrageService;
import com.game.sqlgame.service.SpielerService;
import com.game.sqlgame.service.SpielstandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.Data;
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

    public MainController(JdbcTemplate jdbcTemplate, SpielerService spielerService, FrageService frageService, SpielstandService spielstandService, AntwortService antwortService, Spieler aktivSpieler) {
        this.jdbcTemplate = jdbcTemplate;
        this.spielerService = spielerService;
        this.frageService = frageService;
        this.spielstandService = spielstandService;
        this.antwortService = antwortService;
        this.aktivSpieler = aktivSpieler;
    }

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("spieler", new Spieler());
        return "start";
    }

    @PostMapping("/level1")
    public String login(@ModelAttribute("spieler") Spieler spieler, Model model) {

        log.info(spieler.getName() + "name ####");
        log.info(spieler.getPasswort() +"pass ####");
        // check Login-Daten
        if (!spielerService.existsByName(spieler.getName())){
            model.addAttribute("error", "der Username existiert nicht");
            return "start";
        }
        if (!spieler.getPasswort().equals(spielerService.getPlayerByName(spieler.getName()).get().getPasswort())){
            model.addAttribute("error", "falsche Passwort");
            return "start";
        }

        spieler = spielerService.getPlayerByName(spieler.getName()).get();
        aktivSpieler.setId(spieler.getId());
        aktivSpieler.setName(spieler.getName());

        log.info(aktivSpieler.getName()+"############" + "id ##" +aktivSpieler.getId() );
        Spielstand spielstand = spielstandService.findSpielStandByPlayerId(aktivSpieler.getId()).get();
        log.info(spielstand.toString());
        model.addAttribute("spielstand", spielstand);

        List<Frage> fragen = frageService.findAllQuestionsIdGreater(spielstand.getAktuelleFrageId());
        List<String> listFragenText = fragen.stream().map(Frage::getText).toList();
        List<Integer> antworten = fragen.stream().map(Frage::getAntw_id).toList();


        model.addAttribute("listFragenText", listFragenText);



        log.info(spieler.toString());
        return "level1";
    }

    private boolean checkAntwort (String spieler_antwort, int antwortId ){

        Antwort antwort = antwortService.findAnswerById(antwortId).get();

        if (antwort.getAntwortTyp() == 1) // zahl
        {
            try {
                int spieler_count = jdbcTemplate.queryForObject(spieler_antwort, Integer.class);
                int count = jdbcTemplate.queryForObject(antwort.getSQL(), Integer.class);
                if (count == spieler_count)
                    return true;
                else return false;
            }catch (DataAccessException exception){
                return false;
            }
        }

        if (antwort.getAntwortTyp() == 2) // objekt
        {
            try{

            }catch (DataAccessException e){
                return false;
            }
        }



        return false;
    }


}
