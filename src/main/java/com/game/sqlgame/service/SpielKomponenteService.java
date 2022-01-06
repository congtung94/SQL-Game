package com.game.sqlgame.service;

import com.game.sqlgame.model.SpielKomponente;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface SpielKomponenteService {
    boolean save (SpielKomponente spielKomponente);
    Optional<SpielKomponente> findSpielKomponenteByPlayerId (int id);
    boolean updateSpielKomponente (int spielerId, int komponente, int menge);
}
