package org.clock.in.dao;

import org.clock.in.mapper.ClockInRowMapper;
import org.clock.in.model.ClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class JdbcClockInDao {

    @Autowired
    private JdbcTemplate template;

    public boolean insert(ClockIn clockIn) {
        String sql = "INSERT INTO clock_in (PIS, CLOCKIN_DATE) VALUES (?, ?)";
        template.update(sql, new Object[]{clockIn.getPis(), clockIn.getLocalDateTime().toString()});
        return true;
    }

    public LocalDateTime getLastClockIn(LocalDateTime date, String pis) {
        String sql = "SELECT max(clockin_time) from clock_in.clock_in where pis = '?' and date(clockin_time) = '?%'";
        sql = String.format(sql, pis, date.toString());
        ClockIn clockIn = (ClockIn) template.queryForObject(sql, new ClockInRowMapper());
        if (clockIn == null) return null;
        return clockIn.getLocalDateTime();
    }

    public List<ClockIn> get(LocalDateTime date, String pis) {

        String sql = "SELECT * FROM work_time WHERE pis = '?' and date(work_date) = '?'";
        sql = String.format(sql, pis, date.toString());
        return template.query(sql, new BeanPropertyRowMapper(ClockIn.class));
    }

}
