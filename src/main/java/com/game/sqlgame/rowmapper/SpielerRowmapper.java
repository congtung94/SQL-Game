package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Spieler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpielerRowmapper implements RowMapper<Spieler> {
    @Override
    public Spieler mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spieler spieler = new Spieler();
        spieler.setId(rs.getInt("id"));
        spieler.setName(rs.getString("name"));
        spieler.setPasswort(rs.getString("passwort"));
        return spieler;
    }
}
