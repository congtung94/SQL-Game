package com.game.sqlgame.model;

import lombok.Data;

@Data
public class Produkt {
    private int id;
    private String name;
    private int menge;
    private int besitzerId;
    private int katId;
}
