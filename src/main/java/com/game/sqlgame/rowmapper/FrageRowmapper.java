package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Frage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FrageRowmapper implements RowMapper<Frage> {
    @Override
    public Frage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Frage frage = new Frage();
        frage.setId(rs.getInt("id"));
        frage.setText(rs.getString("text"));
        frage.setPunkte(rs.getInt("punkte"));
        frage.setAntw(rs.getString("antw"));
        frage.setTips(rs.getString("tips"));
        return frage;
    }
}
