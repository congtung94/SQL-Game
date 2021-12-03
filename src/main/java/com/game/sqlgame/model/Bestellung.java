package com.game.sqlgame.model;

import lombok.Data;

import java.util.Date;

@Data
public class Bestellung {
    private int id;
    private int kaeuferId;
    private int verkaeuferId;
    private Date bstTag;
    private Date lieferungTag;
    private int lief_id;
}
