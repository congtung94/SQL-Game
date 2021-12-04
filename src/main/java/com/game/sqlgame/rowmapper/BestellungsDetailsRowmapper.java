package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Bestellungsdetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BestellungsDetailsRowmapper implements RowMapper<Bestellungsdetails> {
    @Override
    public Bestellungsdetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bestellungsdetails bestellungsdetails = new Bestellungsdetails();
        bestellungsdetails.setBstId(rs.getInt("bst_id"));
        bestellungsdetails.setProdId(rs.getInt("prod_id"));
        bestellungsdetails.setEinzelPreis(rs.getFloat("einzel_preis"));
        bestellungsdetails.setBstMenge(rs.getInt("bst_menge"));
        return bestellungsdetails;
    }
}
