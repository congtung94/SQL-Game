package com.game.sqlgame.repository;

import com.game.sqlgame.model.SpielKomponente;
import com.game.sqlgame.model.Spielstand;
import com.game.sqlgame.rowmapper.SpielKomponenteRowmapper;
import com.game.sqlgame.rowmapper.SpielstandRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpielKomponenteRepository {
    private static final Logger log = LoggerFactory.getLogger(SpielerRepository.class);
    private final JdbcTemplate jdbcTemplate;


    public SpielKomponenteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save (SpielKomponente spielKomponente)
    {
        String sql = "insert into spiel_komponente(spieler_id, name,menge) values (?,?,?)";
        int insert = jdbcTemplate.update(sql, spielKomponente.getSpieler_id(), spielKomponente.getName(),
                spielKomponente.getMenge());
        if (insert == 1){
            return true;
        }
        else return false;
    }

    public Optional<SpielKomponente> findSpielKomponenteByPlayerId (int id){
        String sql = "select * from spiel_komponente where spieler_id = ?";
        SpielKomponente spielKomponente = null;
        try {
            spielKomponente = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SpielKomponenteRowmapper());
        }catch (DataAccessException ex) {
            log.info("spielstand not found: " + id);
        }
        return Optional.ofNullable(spielKomponente);
    }

    public boolean updateSpielKomponente (int spielerId, int komponente, int menge){
        int tmp = jdbcTemplate.
                update("update spiel_komponente set name = ?, menge = ? where spieler_id = ?",
                        komponente, menge, spielerId);
        if (tmp == 1){
            return true;
        }
        return false;
    }
}
