package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Bestellung;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BestellungRowmapper implements RowMapper<Bestellung> {
    @Override
    public Bestellung mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bestellung bestellung = new Bestellung();
        bestellung.setId(rs.getInt("id"));
        bestellung.setKaeuferId(rs.getInt("kaeufer_id"));
        bestellung.setVerkaeuferId(rs.getInt("verkaeufer_id"));
        bestellung.setBstTag(rs.getDate("bst_tag"));
        bestellung.setLieferungTag(rs.getDate("lieferung_tag"));
        bestellung.setLief_id(rs.getInt("lieferant_id"));
        return bestellung;
    }
}
