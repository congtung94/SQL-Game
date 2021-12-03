package com.game.sqlgame.controller;


import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.repository.FrageRepository;
import com.game.sqlgame.repository.SpielerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import java.util.List;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private JdbcTemplate jdbcTemplate;
    private final SpielerRepository spielerRepository;
    private final FrageRepository frageRepository;


    public MainController(JdbcTemplate jdbcTemplate, SpielerRepository spielerRepository, FrageRepository frageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.spielerRepository = spielerRepository;
        this.frageRepository = frageRepository;
    }

    @GetMapping("/")
    public String start(Model model, HttpSession session) {
        model.addAttribute("spieler", new Spieler());
        return "start";
    }

    @PostMapping("/level1")
    public String login(@ModelAttribute("spieler") Spieler spieler, Model model, HttpSession session) {
        spielerRepository.save(spieler);

        List<Frage> fragen = frageRepository.findAllQuestions();
        List<String> listFragenText = fragen.stream().map(Frage::getText).toList();
        List<Integer> antworten = fragen.stream().map(Frage::getAntw_id).toList();
        listFragenText.forEach(System.out::println);
        //model.addAttribute("listFragenText", listFragenText);
        model.addAttribute("fragen", fragen);
        model.addAttribute("antworten", antworten);

        model.addAttribute("antwort", new Antwort(null));

        session.setAttribute("spieler",spieler);
        session.setAttribute("listFragenText",listFragenText);
        session.setAttribute("spieler",spieler);
        log.info(spieler.toString());
        return "level1";
    }


    @RequestMapping(value = "/level1-antwort", method = RequestMethod.POST)
    public String postSQL(Model map, @ModelAttribute("antwort") Antwort antwort) {
        map.addAttribute("bewertung", antwort.getSQL() + "  #### richtig oder false #####");
        log.info(antwort.getSQL());
        // change "myview" to the name of your view
        return "level1";
    }


    /*@PostMapping("/checkAnswer")
    public String isValid(@RequestParam String antwort) {
        log.info("######" + antwort);
        return "redirect:/level1";
    }*/

    public boolean checkSQL() {
        return false;
    }
}
