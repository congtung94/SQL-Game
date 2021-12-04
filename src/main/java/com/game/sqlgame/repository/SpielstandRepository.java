package com.game.sqlgame.repository;

import com.game.sqlgame.game_components.Spielstand;
import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.rowmapper.SpielerRowmapper;
import com.game.sqlgame.rowmapper.SpielstandRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpielstandRepository {

    private static final Logger log = LoggerFactory.getLogger(SpielerRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public SpielstandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional<Spielstand> findSpielstandByPlayerId (int id){
        String sql = "select * from spielstand where spieler_id = ?";
        Spielstand spielstand = null;
        try {
            spielstand = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SpielstandRowmapper());
        }catch (DataAccessException ex) {
            log.info("spielstand not found: " + id);
        }
        return Optional.ofNullable(spielstand);
    }
}
