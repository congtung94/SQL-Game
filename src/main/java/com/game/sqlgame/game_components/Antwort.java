package com.game.sqlgame.game_components;

import lombok.Data;

@Data
public class Antwort {
    private int id;
    private int colAnz;
    private int zeileAnz;
    private String SQL;

    public Antwort (String SQL){
        this.SQL = SQL;
    }
}
