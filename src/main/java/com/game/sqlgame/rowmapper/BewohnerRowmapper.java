package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Bewohner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BewohnerRowmapper implements RowMapper<Bewohner> {
    @Override
    public Bewohner mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bewohner bewohner = new Bewohner();
        bewohner.setId(rs.getInt("id"));
        bewohner.setName(rs.getString("name"));
        bewohner.setOrtId(rs.getInt("ort_id"));
        bewohner.setGold(rs.getInt("gold"));
        bewohner.setBeruf(rs.getString("beruf"));
        bewohner.setStatus(rs.getString("status"));
        return bewohner;
    }
}
