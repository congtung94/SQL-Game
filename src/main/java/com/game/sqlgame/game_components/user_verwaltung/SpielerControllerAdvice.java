package com.game.sqlgame.game_components.user_verwaltung;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SpielerControllerAdvice {

    @ModelAttribute("aktuellerSpieler")
    public AktuellerSpieler getAktuellerSpieler (Authentication authentication){
        return (authentication == null) ? null :(AktuellerSpieler) authentication.getPrincipal();
    }
}
