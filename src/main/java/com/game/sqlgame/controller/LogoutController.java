package com.game.sqlgame.controller;

import com.game.sqlgame.gameComponents.user_verwaltung.AktuellerSpieler;
import com.game.sqlgame.service.SpielstandService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    private static final Logger log = LoggerFactory.getLogger(LogoutController.class);
    private final SpielstandService spielstandService;

    public LogoutController(SpielstandService spielstandService) {
        this.spielstandService = spielstandService;
    }


    @PostMapping("/custom_logout")
    public String logout (@RequestParam String dataToLogout, Model model, HttpServletRequest request){
        log.info("hier logout controller " + dataToLogout);
        JSONObject dataJson = new JSONObject(dataToLogout);
        AktuellerSpieler aktuellerSpieler =(AktuellerSpieler) model.asMap().get("aktuellerSpieler");
        log.info("hier logout controller "+aktuellerSpieler.toString()+"\n" +
                dataJson.getInt("level")+", "+dataJson.getInt("punkte")+ ", "+
                dataJson.getInt("zeit")+", "+dataJson.getInt("aktFrag"));

        // update level, punkte, zeit, aktuelle Frage vor Logout
        spielstandService.updateSpielstand(aktuellerSpieler.getId(), dataJson.getInt("level"),
                dataJson.getInt("punkte"), dataJson.getInt("zeit"), dataJson.getInt("aktFrag"));

        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }

        return "redirect:/";
    }
}
