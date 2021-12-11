package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Produkt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduktRowmapper implements RowMapper<Produkt> {
    @Override
    public Produkt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Produkt produkt = new Produkt();
        produkt.setId(rs.getInt("id"));
        produkt.setName(rs.getString("name"));
        produkt.setMenge(rs.getInt("menge"));
        produkt.setBesitzerId(rs.getInt("besitzer_id"));
        produkt.setKatId(rs.getInt("kat_id"));
        return produkt;
    }
}
