package com.game.sqlgame.model;

import lombok.Data;

@Data
public class Bewohner {
    private int id;
    private String name;
    private int ortId;
    private int gold;
    private String beruf;
    private String status;
}
