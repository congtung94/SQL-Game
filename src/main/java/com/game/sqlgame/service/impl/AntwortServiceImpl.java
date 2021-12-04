package com.game.sqlgame.service.impl;

import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.repository.AntwortRepository;
import com.game.sqlgame.service.AntwortService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AntwortServiceImpl implements AntwortService {

    private final AntwortRepository antwortRepository;

    public AntwortServiceImpl(AntwortRepository antwortRepository) {
        this.antwortRepository = antwortRepository;
    }


    @Override
    public Optional<Antwort> findAnswerById(int id) {
        return antwortRepository.findAnswerById(id);
    }
}
