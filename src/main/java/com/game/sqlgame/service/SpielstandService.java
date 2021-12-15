package com.game.sqlgame.service;

import com.game.sqlgame.gameComponents.Spielstand;

import java.util.Optional;

public interface SpielstandService {
    Optional<Spielstand> findSpielStandByPlayerId (int id);
    boolean updateSpielstand (int spielstandId,  int level, int plusPunkte, int aktuelleFrageId);
    boolean updateSpielstand (int spielstandId, int neuPunkte, int aktuelleFrageId);
    boolean updateSpielstand (int spielstandId, int neuPunkte);
    boolean save (Spielstand spielstand);
    boolean updateSpielstandBySpielerId (int spielerId, int neuZeit);
    boolean updateSpielstadNeustart (int spielerId, int level, int punkte, int zeit, int aktuelleFrageId);
    boolean updateNeueFrageId (int spielerId, int aktuelleFrageId);
}
