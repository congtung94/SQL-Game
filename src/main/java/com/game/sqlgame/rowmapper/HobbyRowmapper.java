package com.game.sqlgame.rowmapper;

import com.game.sqlgame.model.Hobby;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HobbyRowmapper implements RowMapper<Hobby> {
    @Override
    public Hobby mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hobby hobby = new Hobby();
        hobby.setId(rs.getInt("id"));
        hobby.setName(rs.getString("name"));
        hobby.setBeschr(rs.getString("beschr"));
        return hobby;
    }
}
