package com.game.sqlgame.service;

import com.game.sqlgame.game_components.Frage;

import java.util.List;
import java.util.Optional;

public interface FrageService {
    Optional<Frage> findQuestionById (int id);
    List<Frage> listAllQuestions();
    boolean existsById (int id);
}
