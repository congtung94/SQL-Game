package com.game.sqlgame.service;

import com.game.sqlgame.game_components.Spielstand;

import java.util.Optional;

public interface SpielstandService {
    Optional<Spielstand> findSpielStandByPlayerId (int id);
    public boolean updateSpielstand (int spielstandId,  int level, int plusPunkte, int aktuelleFrageId);
    public boolean updateSpielstand (int spielstandId, int neuPunkte, int aktuelleFrageId);
    public boolean updateSpielstand (int spielstandId, int neuPunkte);
}
