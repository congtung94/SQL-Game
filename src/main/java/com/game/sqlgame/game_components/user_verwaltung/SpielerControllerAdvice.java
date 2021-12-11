package com.game.sqlgame.game_components.user_verwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SpielerControllerAdvice {

    @Autowired
    Spieler aktivSpieler;

    @ModelAttribute("aktivSpieler")
    public Spieler getCurrentPlayer (){
        return aktivSpieler;
    }
}
