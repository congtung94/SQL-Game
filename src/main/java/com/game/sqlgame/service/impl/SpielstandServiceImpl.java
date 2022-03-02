package com.game.sqlgame.service.impl;

import com.game.sqlgame.model.Spielstand;
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
    public boolean updateSpielstand(int spieler_id, int neuPunkte, int neueZeit) {
        return spielstandRepository.updateSpielstand(spieler_id, neuPunkte,neueZeit);
    }


    @Override
    public boolean updateSpielstand(int spielerId, int level, int punkte, int zeit, int aktuelleFrageId) {
        return spielstandRepository.updateSpielstand(spielerId,level,punkte,zeit,aktuelleFrageId);
    }

    @Override
    public boolean save(Spielstand spielstand) {
        return spielstandRepository.save(spielstand);
    }


}
