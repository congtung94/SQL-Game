package com.game.sqlgame.service;

import com.game.sqlgame.gameComponents.Antwort;

import java.util.Optional;

public interface AntwortService {
    Optional<Antwort> findAnswerById (int id);
}
