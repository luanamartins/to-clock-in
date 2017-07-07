package org.clock.in.dao;

import org.clock.in.mapper.ClockInRowMapper;
import org.clock.in.model.ClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class JdbcClockInDao {

    @Autowired
    private JdbcTemplate template;

    public boolean insert(ClockIn clockIn) {
        String sql = "insert into clock_in (pis, clockin_time) values ('%s', '%s')";
        sql = String.format(sql, clockIn.getPis(), clockIn.getLocalDateTime().toString());
        template.update(sql);
        return true;
    }

    public LocalDateTime getLastClockIn(LocalDateTime localDateTime, String pis) {
        String sql = "select pis, max(clockin_time) as clockin_time from clock_in where pis = '%s' and date(clockin_time) = '%s%%'";

        String localDateTimeStr = localDateTime.toString();
        int lastIndexForDate = localDateTimeStr.indexOf("T");
        String dateStr = localDateTimeStr.substring(0, lastIndexForDate);

        sql = String.format(sql, pis, dateStr);
        List result = template.queryForList(sql);
        if (result != null || !result.isEmpty()) {
            Map map = (Map) result.get(0);
            Timestamp timeStr = (Timestamp) map.get("clockin_time");
            return timeStr.toLocalDateTime();
        } else {
            return null;
        }

    }

    public List<ClockIn> get(LocalDateTime date, String pis) {

        String sql = "SELECT * FROM work_time WHERE pis = '?' and date(work_date) = '?'";
        sql = String.format(sql, pis, date.toString());
        return template.query(sql, new BeanPropertyRowMapper(ClockIn.class));
    }

}
