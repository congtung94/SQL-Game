package com.game.sqlgame.rowmapper;

import com.game.sqlgame.gameComponents.Antwort;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AntwortRowmapper implements RowMapper<Antwort> {

    @Override
    public Antwort mapRow(ResultSet rs, int rowNum) throws SQLException {
        Antwort antwort = new Antwort();
        antwort.setId(rs.getInt("id"));
        antwort.setColAnz(rs.getInt("col_anz"));
        antwort.setZeileAnz(rs.getInt("zeile_anz"));
        antwort.setAntwortTyp(rs.getInt("typ"));
        antwort.setSQL(rs.getString("sql"));
        return antwort;
    }
}
