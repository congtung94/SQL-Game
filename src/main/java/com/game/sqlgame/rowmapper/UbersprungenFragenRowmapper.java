package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.UbersprungenFragen;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UbersprungenFragenRowmapper implements RowMapper<UbersprungenFragen> {
    @Override
    public UbersprungenFragen mapRow(ResultSet rs, int rowNum) throws SQLException {
        UbersprungenFragen ubersprungen = new UbersprungenFragen();
        ubersprungen.setSpieler_id(rs.getInt("spieler_id"));
        ubersprungen.setFrageId(rs.getInt("frageId"));
        return ubersprungen;
    }
}
