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

    @Override
    public boolean updateSpielstand(int spielstandId, int level, int neuPunkte, int aktuelleFrageId) {
        return spielstandRepository.updateSpielstand(spielstandId, level,neuPunkte,aktuelleFrageId);
    }

    @Override
    public boolean updateSpielstand(int spielstandId, int neuPunkte, int aktuelleFrageId) {
        return spielstandRepository.updateSpielstand(spielstandId, neuPunkte,aktuelleFrageId);
    }

    @Override
    public boolean updateSpielstand(int spielstandId, int neuPunkte) {
        return spielstandRepository.updateSpielstand(spielstandId,neuPunkte);
    }
}
