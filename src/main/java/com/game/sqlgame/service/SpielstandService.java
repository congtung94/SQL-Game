package com.game.sqlgame.service;

import com.game.sqlgame.model.Spielstand;

import java.util.Optional;

public interface SpielstandService {
    Optional<Spielstand> findSpielStandByPlayerId (int id);
    boolean updateSpielstand (int spieler_id, int neuPunkte, int neueZeit);
    boolean updateSpielstand(int spielerId, int level, int punkte, int zeit, int aktuelleFrageId);
    boolean save (Spielstand spielstand);

}
