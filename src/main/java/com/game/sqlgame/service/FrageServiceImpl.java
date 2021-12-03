package com.game.sqlgame.service;

import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.repository.FrageRepository;

import java.util.List;
import java.util.Optional;

public class FrageServiceImpl implements FrageService {

    private final FrageRepository frageRepository;

    public FrageServiceImpl(FrageRepository frageRepository) {
        this.frageRepository = frageRepository;
    }

    @Override
    public Optional<Frage> findQuestionById(int id) {
        return frageRepository.findQuestionById(id);
    }

    @Override
    public List<Frage> listAllQuestions() {
        return frageRepository.findAllQuestions();
    }

    @Override
    public boolean existsById(int id) {
        return frageRepository.existsById(id);
    }
}
