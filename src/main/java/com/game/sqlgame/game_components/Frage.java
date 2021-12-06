package com.game.sqlgame.game_components;

import lombok.Data;

@Data
public class Frage {
    private int id;
    private String text;
    private int level;
    private int max_punkte;
    private int antw_id;
}
