package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.SpielKomponente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpielKomponenteRowmapper implements RowMapper<SpielKomponente> {
    @Override
    public SpielKomponente mapRow(ResultSet rs, int rowNum) throws SQLException {
        SpielKomponente spielKomponente = new SpielKomponente();
        spielKomponente.setSpieler_id(rs.getInt("spieler_id"));
        spielKomponente.setName(rs.getString("name"));
        spielKomponente.setMenge(rs.getInt("menge"));
        return spielKomponente;
    }
}
