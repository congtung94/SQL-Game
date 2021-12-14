package com.game.sqlgame.repository;

import com.game.sqlgame.game_components.user_verwaltung.Spieler;
import com.game.sqlgame.rowmapper.SpielerRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SpielerRepository {

    private static final Logger log = LoggerFactory.getLogger(SpielerRepository.class);
    private JdbcTemplate jdbcTemplate;

    public SpielerRepository (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save (Spieler spieler){
        String sql = "insert into Spieler(name,passwort) values (?,?)";
        int insert = jdbcTemplate.update(sql, spieler.getName(), new BCryptPasswordEncoder().encode(spieler.getPasswort()));
        if (insert == 1){
            log.info("neuer Spieler ");
            return true;
        }
        else return false;
    }

    public Optional<Spieler> getPlayerById (int id){
        String sql = "SELECT id,name,passwort from spieler where id = ?";
        Spieler spieler = null;
        try {
            spieler = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SpielerRowmapper());
        }catch (DataAccessException ex) {
            log.info("Spieler not found: " + id);
        }
        return Optional.ofNullable(spieler);
    }

    public Optional<Spieler> getPlayerByName (String name){
        String sql = "SELECT id,name,passwort from spieler where name = ?";
        Spieler spieler = null;
        try {
            spieler = jdbcTemplate.queryForObject(sql, new Object[]{name}, new SpielerRowmapper());
        }catch (DataAccessException ex) {
            log.info("Spieler not found: " + name);
        }
        return Optional.ofNullable(spieler);
    }

    public boolean existsByName (String name){
        return getPlayerByName(name).isPresent();
    }

    public List<Spieler> getAllPlayers (){
        String sql = "select * form spieler";
        return jdbcTemplate.query(sql, new SpielerRowmapper());
    }


}
