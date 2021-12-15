package com.game.sqlgame.repository;

import com.game.sqlgame.gameComponents.Spielstand;
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

    public boolean save (Spielstand spielstand)
    {
        String sql = "insert into spielstand(spieler_id, level,punkte, zeit, akt_frage_id) values (?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql, spielstand.getSpielerId(), spielstand.getLevel(),
                spielstand.getPunkte(), spielstand.getZeit(), spielstand.getAktuelleFrageId());
        if (insert == 1){
            return true;
        }
        else return false;
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

    public boolean updateSpielstand (int spielstandId,  int level, int neuPunkte, int aktuelleFrageId){

        int tmp = jdbcTemplate.
                update("update spielstand set level = ?, punkte = ?, akt_frage_id =? where spl_std_id = ?",
                        level, neuPunkte , aktuelleFrageId, spielstandId);
        if (tmp == 1){
            return true;
        }
        return false;
    }

    public boolean updateSpielstand (int spielstandId, int neuPunkte, int aktuelleFrageId){
        int tmp = jdbcTemplate.
                update("update spielstand set punkte = ?, akt_frage_id =? where spl_std_id = ?",
                         neuPunkte, aktuelleFrageId, spielstandId);
        if (tmp == 1){
            return true;
        }
        return false;
    }

    public boolean updateSpielstand (int spielstandId, int neuPunkte){
        int tmp = jdbcTemplate.
                update("update spielstand set punkte = ? where spl_std_id = ?",
                        neuPunkte, spielstandId);
        if (tmp == 1){
            return true;
        }
        return false;
    }

    public boolean updateSpielstandBySpielerId (int spielerId, int neuZeit){
        int tmp = jdbcTemplate.
                update("update spielstand set zeit = ? where spieler_id = ?",
                        neuZeit, spielerId);
        if (tmp == 1){
            return true;
        }
        return false;
    }

    public boolean updateSpielstadNeustart(int spielerId, int level, int punkte, int zeit, int aktuelleFrageId){
        int tmp = jdbcTemplate.
                update("update spielstand set level = ?, punkte = ?, zeit = ?, " +
                                "akt_frage_id = ? where spieler_id = ?",
                        level, punkte, zeit,aktuelleFrageId, spielerId);
        if (tmp == 1){
            return true;
        }
        return false;
    }

    public boolean updateNeueFrageId (int spielerId, int aktuelleFrageId){
        int tmp = jdbcTemplate.
                update("update spielstand set akt_frage_id = ? where spieler_id = ?",
                        aktuelleFrageId, spielerId);
        if (tmp == 1){
            return true;
        }
        return false;
    }
}
