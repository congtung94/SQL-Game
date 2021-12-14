package com.game.sqlgame.service;

import com.game.sqlgame.game_components.user_verwaltung.Spieler;

import java.util.List;
import java.util.Optional;

public interface SpielerService {
    Optional<Spieler>  getPlayerById (int id);
    Optional<Spieler> getPlayerByName (String name);
    boolean existsByName (String name);
    List<Spieler> getAllPlayers();
    boolean save (Spieler spieler);
}
