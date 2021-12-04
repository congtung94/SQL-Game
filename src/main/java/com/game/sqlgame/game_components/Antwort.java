package com.game.sqlgame.game_components;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Antwort {
    private int id;
    private int colAnz;
    private int zeileAnz;
    private String SQL;
    private int antwortTyp; // 1- Zahl, 2-Objekt, 3- List

    public Antwort (String SQL){
        this.SQL = SQL;
    }
}
