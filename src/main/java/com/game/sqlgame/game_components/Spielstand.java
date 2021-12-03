package com.game.sqlgame.game_components;

import lombok.Data;

@Data
public class Spielstand {
    private int spielStandId;
    private int spielerId;
    private int level;
    private int punkte;
    private int zeit;
    private int aktuelleFrageId;
}
