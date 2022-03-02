package com.game.sqlgame.repository;

import com.game.sqlgame.model.Frage;
import com.game.sqlgame.model.UbersprungenFragen;
import com.game.sqlgame.rowmapper.FrageRowmapper;
import com.game.sqlgame.rowmapper.UbersprungenFragenRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UbersprungenFragenRepository {
    private static final Logger log = LoggerFactory.getLogger(SpielerRepository.class);
    private final JdbcTemplate jdbcTemplate;


    public UbersprungenFragenRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save (UbersprungenFragen ubersprungenFrage)
    {
        String sql = "insert into ubersprungenFragen(spieler_id, frage_id) values (?,?)";
        int insert = jdbcTemplate.update(sql, ubersprungenFrage.getSpieler_id(), ubersprungenFrage.getFrageId());
        if (insert == 1){
            return true;
        }
        else return false;
    }

    public List<Integer> getUberspringenFrageWithSpielerId(int spielerId){
        String sql = "select frage_id " +
                "from ubersprungenFragen " +
                "where spieler_id = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{spielerId}, Integer.class);
    }

    public boolean deleteUbersprungenFrage(int spielerId, int frageId){
        String sql = "delete from ubersprungenFragen where spieler_id = ? and frage_id = ?";
        return jdbcTemplate.update(sql, new Object[]{spielerId,frageId}) == 1;
    }

    public boolean deleteAllUbersprungenFrage (int spielerId){
        String sql = "delete from ubersprungenFragen where spieler_id = ?";
        return jdbcTemplate.update(sql, new Object[]{spielerId}) == 1;
    }

    public boolean existsUberspringenFrage (int spielerId){
        return !getUberspringenFrageWithSpielerId(spielerId).isEmpty();
    }
}
