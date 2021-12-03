package com.game.sqlgame.controller;


import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.repository.FrageRepository;
import com.game.sqlgame.repository.SpielerRepository;
import com.game.sqlgame.service.SpielerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private JdbcTemplate jdbcTemplate;

    private final SpielerService spielerService;
    private final FrageRepository frageRepository;




    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("spieler", new Spieler());
        return "start";
    }

    @PostMapping("/level1")
    public String login(@ModelAttribute("spieler") Spieler spieler, Model model) {





        List<Frage> fragen = frageRepository.findAllQuestions();
        List<String> listFragenText = fragen.stream().map(Frage::getText).toList();
        List<Integer> antworten = fragen.stream().map(Frage::getAntw_id).toList();
        listFragenText.forEach(System.out::println);
        //model.addAttribute("listFragenText", listFragenText);
        model.addAttribute("fragen", fragen);
        model.addAttribute("antworten", antworten);

        model.addAttribute("antwort", new Antwort(null));

        log.info(spieler.toString());
        return "level1";
    }


}
