package com.game.sqlgame.service;

import com.game.sqlgame.model.Frage;
import com.game.sqlgame.model.UbersprungenFragen;

import java.util.List;
import java.util.Optional;

public interface UbersprungenFragenService {
    boolean save (UbersprungenFragen ubersprungenFrage);
    List<Frage> getUberspringenFrageWithSpielerId(int spielerId);
    boolean existsUberspringenFrage (int spielerId);
}
