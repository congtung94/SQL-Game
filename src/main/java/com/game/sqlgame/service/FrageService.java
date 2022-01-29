package com.game.sqlgame.service;

import com.game.sqlgame.model.Frage;

import java.util.List;
import java.util.Optional;

public interface FrageService {
    Optional<Frage> findQuestionById (int id);
    List<Frage> findAllQuestions();
    List<Frage> findAllQuestionsIdGreater (int id);
    boolean existsById (int id);
    int findAnswerIdByQuestionId(int id);
    int countFrage();

}
