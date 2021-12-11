package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Kategorie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KategorieRowmapper implements RowMapper<Kategorie> {
    @Override
    public Kategorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Kategorie kategorie = new Kategorie();
        kategorie.setId(rs.getInt("id"));
        kategorie.setName(rs.getString("name"));
        kategorie.setBeschr(rs.getString("beschr"));
        return kategorie;
    }
}
