package com.game.sqlgame.gameComponents;

import lombok.Data;

@Data
public class Spielstand {
    private int spielStandId;
    private int spielerId;
    private int level;
    private int punkte;
    private int zeit;
    private int aktuelleFrageId;

    public Spielstand(){
        this.level = 1;
        this.punkte = 0;
        this.zeit = 0;
        this.aktuelleFrageId = 1;
    }
}
