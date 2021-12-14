package com.game.sqlgame.service.impl;

import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.repository.SpielerRepository;
import com.game.sqlgame.service.SpielerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpielerServiceImpl implements SpielerService {

    private final SpielerRepository spielerRepository;

    public SpielerServiceImpl(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
    }

    @Override
    public Optional<Spieler> getPlayerById(int id) {
        return spielerRepository.getPlayerById(id);
    }

    @Override
    public Optional<Spieler> getPlayerByName(String name) {
        return spielerRepository.getPlayerByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return spielerRepository.existsByName(name);
    }

    @Override
    public List<Spieler> getAllPlayers() {
        return spielerRepository.getAllPlayers();
    }

    @Override
    public boolean save(Spieler spieler) {
        return spielerRepository.save(spieler);
    }
}
