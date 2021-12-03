package com.game.sqlgame.model;

import lombok.Data;

@Data
public class Bestellungsdetails {
    private int bstId;
    private int prodId;
    private float einzelPreis;
    private int bstMenge;
}
