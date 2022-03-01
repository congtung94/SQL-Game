package com.game.sqlgame.model;

import lombok.Data;

@Data
public class Frage {
    private int id;
    private String text;
    private int punkte;
    private String antw;
    private String tips;
}
