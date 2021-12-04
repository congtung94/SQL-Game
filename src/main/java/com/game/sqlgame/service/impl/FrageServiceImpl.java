package com.game.sqlgame.service.impl;

import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.repository.FrageRepository;
import com.game.sqlgame.service.FrageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<Frage> findAllQuestions() {
        return frageRepository.findAllQuestions();
    }

    @Override
    public List<Frage> findAllQuestionsIdGreater(int id) {
        return frageRepository.findAllQuestionsIdGreater(id);
    }

    @Override
    public boolean questionWithoutAnswer(int id) {
        return frageRepository.questionWithoutAnswer(id);
    }

    @Override
    public boolean existsById(int id) {
        return frageRepository.existsById(id);
    }
}