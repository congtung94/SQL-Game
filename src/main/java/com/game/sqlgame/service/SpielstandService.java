package com.game.sqlgame.service;

import com.game.sqlgame.game_components.Spielstand;

import java.util.Optional;

public interface SpielstandService {
    Optional<Spielstand> findSpielStandByPlayerId (int id);
}
