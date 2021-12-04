package com.game.sqlgame.service.impl;

import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.repository.SpielstandRepository;
import com.game.sqlgame.service.SpielstandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpielstandServiceImpl implements SpielstandService {

    private final SpielstandRepository spielstandRepository;

    public SpielstandServiceImpl(SpielstandRepository spielstandRepository) {
        this.spielstandRepository = spielstandRepository;
    }


    @Override
    public Optional<Spielstand> findSpielStandByPlayerId(int id) {
        return spielstandRepository.findSpielstandByPlayerId(id);
    }
}
