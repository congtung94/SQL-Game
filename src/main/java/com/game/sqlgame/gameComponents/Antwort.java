package com.game.sqlgame.gameComponents;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Antwort {
    private int id;
    private int colAnz;
    private int zeileAnz;
    private int antwortTyp; // 1- Zahl, 2-Objekt, 3- List
    private String SQL;


    public Antwort (String SQL){
        this.SQL = SQL;
    }
}
