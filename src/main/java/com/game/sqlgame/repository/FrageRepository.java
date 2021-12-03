package com.game.sqlgame.repository;

import com.game.sqlgame.game_components.Frage;
import com.game.sqlgame.rowmapper.FrageRowmapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
