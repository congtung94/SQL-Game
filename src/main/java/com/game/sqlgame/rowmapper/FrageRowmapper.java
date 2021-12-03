package com.game.sqlgame.rowmapper;

import com.game.sqlgame.game_components.Frage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FrageRowmapper implements RowMapper<Frage> {
    @Override
    public Frage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Frage frage = new Frage();
        frage.setId(rs.getInt("id"));
        frage.setText(rs.getString("text"));
        frage.setAntw_id(rs.getInt("antw_id"));
        frage.setLevel(rs.getInt("level"));
        frage.setMax_punkte(rs.getInt("max_punkte"));
        return frage;
    }
}
