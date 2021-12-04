package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Lieferant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LiegerantRowmapper implements RowMapper<Lieferant> {
    @Override
    public Lieferant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lieferant lieferant = new Lieferant();
        lieferant.setId(rs.getInt("id"));
        lieferant.setName(rs.getString("name"));
        lieferant.setBeschr(rs.getString("beschr"));
        return lieferant;
    }
}
