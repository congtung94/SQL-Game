package com.game.sqlgame.service.impl;

import com.game.sqlgame.gameComponents.Spielstand;
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

    @Override
    public boolean save(Spielstand spielstand) {
        return spielstandRepository.save(spielstand);
    }

    @Override
    public boolean updateSpielstandBySpielerId(int spielerId, int neuZeit) {
        return spielstandRepository.updateSpielstandBySpielerId(spielerId,neuZeit);
    }

    @Override
    public boolean updateSpielstadNeustart(int spielerId, int level, int punkte, int zeit, int aktuelleFrageId) {
        return spielstandRepository.updateSpielstadNeustart(spielerId,level,punkte,zeit,aktuelleFrageId);
    }

    @Override
    public boolean updateNeueFrageId(int spielerId, int aktuelleFrageId) {
        return spielstandRepository.updateNeueFrageId(spielerId,aktuelleFrageId);
    }
}
