package com.game.sqlgame.rowmapper;

import com.game.sqlgame.game_components.Spielstand;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpielstandRowmapper implements RowMapper<Spielstand> {

    @Override
    public Spielstand mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spielstand spielstand = new Spielstand();
        spielstand.setSpielStandId(rs.getInt("spl_std_id"));
        spielstand.setSpielerId(rs.getInt("spieler_id"));
        spielstand.setLevel(rs.getInt("level"));
        spielstand.setPunkte(rs.getInt("punkte"));
        spielstand.setZeit(rs.getInt("zeit"));
        spielstand.setAktuelleFrageId(rs.getInt("akt_frage_id"));
        return spielstand;
    }
}
