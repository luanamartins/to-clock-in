package org.clock.in.mapper;

import org.clock.in.model.ClockIn;
import org.clock.in.model.WorkTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkTimeRowMapper implements RowMapper {

    public Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        String pis = resultSet.getString("pis");

        String strDate = resultSet.getString("work_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(strDate, formatter);

        int workHours = resultSet.getInt("work_hours");

        int workMinutes = resultSet.getInt("work_minutes");

        int restMinutes = resultSet.getInt("work_rest_minutes");

        boolean isResting = resultSet.getBoolean("is_resting");

        return new WorkTime(pis, time, workHours, workMinutes, restMinutes, isResting);
    }
}
