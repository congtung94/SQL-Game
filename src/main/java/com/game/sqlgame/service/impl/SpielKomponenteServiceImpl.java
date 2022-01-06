package com.game.sqlgame.service.impl;

import com.game.sqlgame.model.SpielKomponente;
import com.game.sqlgame.repository.SpielKomponenteRepository;
import com.game.sqlgame.service.SpielKomponenteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpielKomponenteServiceImpl implements SpielKomponenteService {

    private final SpielKomponenteRepository spielKomponenteRepository;

    public SpielKomponenteServiceImpl(SpielKomponenteRepository spielKomponenteRepository) {
        this.spielKomponenteRepository = spielKomponenteRepository;
    }

    @Override
    public boolean save(SpielKomponente spielKomponente) {
        return spielKomponenteRepository.save(spielKomponente);
    }

    @Override
    public Optional<SpielKomponente> findSpielKomponenteByPlayerId(int id) {
        return spielKomponenteRepository.findSpielKomponenteByPlayerId(id);
    }

    @Override
    public boolean updateSpielKomponente(int spielerId, int komponente, int menge) {
        return spielKomponenteRepository.updateSpielKomponente(spielerId,komponente,menge);
    }
}
