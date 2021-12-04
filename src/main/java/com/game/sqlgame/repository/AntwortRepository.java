package com.game.sqlgame.repository;

import com.game.sqlgame.game_components.Antwort;
import com.game.sqlgame.rowmapper.AntwortRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AntwortRepository {

    private static final Logger log = LoggerFactory.getLogger(AntwortRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public AntwortRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Antwort> findAnswerById (int id){
        String sql = "select * from Antwort where id = ? ";
        Antwort antwort = null;
        try {
            antwort = jdbcTemplate.queryForObject(sql, new Object[]{id}, new AntwortRowmapper());
        }catch (DataAccessException ex) {
            log.info("Antwort not found: " + id);
        }
        return Optional.ofNullable(antwort);
    }


}
