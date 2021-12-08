package com.game.sqlgame.repository;

import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.rowmapper.FrageRowmapper;
import com.game.sqlgame.rowmapper.SpielerRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FrageRepository {

    private static final Logger log = LoggerFactory.getLogger(FrageRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public FrageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Frage> findAllQuestions (){
        String sql = "select * from frage";
        return jdbcTemplate.query(sql, new FrageRowmapper());
    }

    public List<Frage> findAllQuestionsIdGreater (int id){
        String sql = "select * from frage where id >= ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new FrageRowmapper());
    }

    public Optional<Frage> findQuestionById (int id){
        String sql = "select * from frage where id = ?";
        Frage frage = null;
        try {
            frage = jdbcTemplate.queryForObject(sql, new Object[]{id}, new FrageRowmapper());
        }catch (DataAccessException ex) {
            log.info("Frage not found: " + id);
        }
        return Optional.ofNullable(frage);
    }

    public int findAnswerIdByQuestionId(int id){
        String sql = "select antw_id from frage where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
    }

    public boolean existsById (int id){
        return findQuestionById(id).isPresent();
    }

    public boolean questionWithoutAnswer (int id){
        String sql = "select * from frage where id = ? and antw_id is null";
        Frage frage = null;
        try {
            frage = jdbcTemplate.queryForObject(sql, new Object[]{id}, new FrageRowmapper());
        }catch (DataAccessException ex){
            log.info("Es gibt eine Antwort für die Frage mit der Id " + id);
        }

        return frage != null;
    }

    public int countFrage (){
        return jdbcTemplate.queryForObject("select count(*) from frage", Integer.class);
    }


}
