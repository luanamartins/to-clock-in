package org.clock.in.mapper;

import org.clock.in.model.ClockIn;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockInRowMapper implements RowMapper, Serializable {

    public Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        if (resultSet.next()) {
            String pis = resultSet.getString("pis");
            String strDate = resultSet.getString("clockin_time");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse(strDate, formatter);
            return new ClockIn(pis, time);
        }
        return null;
    }
}
