package com.game.sqlgame.service;

import com.game.sqlgame.game_components.Antwort;

import java.util.Optional;

public interface AntwortService {
    Optional<Antwort> findAnswerById (int id);
}
