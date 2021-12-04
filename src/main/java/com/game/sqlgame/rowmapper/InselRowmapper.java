package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Insel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InselRowmapper implements RowMapper<Insel> {
    @Override
    public Insel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Insel insel = new Insel();
        insel.setId(rs.getInt("id"));
        insel.setName(rs.getString("name"));
        insel.setBeschr(rs.getString("beschr"));
        return insel;
    }
}
