package com.game.sqlgame.game_components.user_verwaltung;

import org.springframework.security.core.authority.AuthorityUtils;

public class AktuellerSpieler extends org.springframework.security.core.userdetails.User{

    private Spieler spieler;

    public AktuellerSpieler(Spieler spieler) {

        super(spieler.getName(),spieler.getPasswort(), AuthorityUtils.createAuthorityList("USER"));
        this.spieler = spieler;
    }

    public Spieler getSpieler (){
        return this.spieler;
    }

    public String getName(){
        return this.spieler.getName();
    }

    public int getId (){
        return this.spieler.getId();
    }

    @Override
    public String toString() {
        return "AktuellerSpieler{" +
                "spieler=" + spieler + "name : " + spieler.getName()+
                '}';
    }
}
