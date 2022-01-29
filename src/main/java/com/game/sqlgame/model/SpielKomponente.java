package com.game.sqlgame.model;

import lombok.Data;

@Data
public class SpielKomponente {
    private int spieler_id;
    private String name;
    private int menge;

    public SpielKomponente (){
        this.name = "Gold";
        this.menge = 1000;
    }
}
