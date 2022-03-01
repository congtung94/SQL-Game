package com.game.sqlgame.service.impl;

import com.game.sqlgame.model.Frage;
import com.game.sqlgame.model.UbersprungenFragen;
import com.game.sqlgame.repository.UbersprungenFragenRepository;
import com.game.sqlgame.service.UbersprungenFragenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbersprungenFragenServiceImpl implements UbersprungenFragenService {

    private final UbersprungenFragenRepository ubersprungenFragenRepository;

    public UbersprungenFragenServiceImpl(UbersprungenFragenRepository ubersprungenFragenRepository) {
        this.ubersprungenFragenRepository = ubersprungenFragenRepository;
    }


    @Override
    public boolean save(UbersprungenFragen ubersprungenFrage) {
        return ubersprungenFragenRepository.save(ubersprungenFrage);
    }

    @Override
    public List<Frage> getUberspringenFrageWithSpielerId(int spielerId) {
        return ubersprungenFragenRepository.getUberspringenFrageWithSpielerId(spielerId);
    }

    @Override
    public boolean existsUberspringenFrage(int spielerId) {
        return ubersprungenFragenRepository.existsUberspringenFrage(spielerId);
    }
}
