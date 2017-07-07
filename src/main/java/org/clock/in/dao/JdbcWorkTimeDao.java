package org.clock.in.dao;

import org.clock.in.mapper.WorkTimeRowMapper;
import org.clock.in.model.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JdbcWorkTimeDao {

    @Autowired
    private JdbcTemplate template;

    public boolean insert(WorkTime workTime) {
        String sql = "insert into work_time (pis, work_date, work_hours, work_minutes, work_rest_minutes) values (?, ?, ?, ?, ?)";
        template.update(sql, new Object[]{workTime.getPis(), workTime.getLocalDateTime().toString(), 0, 0, 0});
        return true;
    }

    public WorkTime get(LocalDateTime localDateTime, String pis) {
        String sql = "select * from work_time where date(work_date) = '%s%%' and pis = '%s'";

        String localDateTimeStr = localDateTime.toString();
        int lastIndexForDate = localDateTimeStr.indexOf("T");
        String dateStr = localDateTimeStr.substring(0, lastIndexForDate);

        sql = String.format(sql, dateStr, pis);
        Object workTime = template.queryForObject(sql, new WorkTimeRowMapper());
        if (workTime == null) return null;

        return (WorkTime) workTime;
    }

    public void updateFirstIn(String pis, boolean firstIn) {
        String sql = "update work_time set first_in = %b where pis = '%s'";
        sql = String.format(sql, firstIn, pis);
        template.update(sql);
    }

    public void updateWorkRestTime(WorkTime workTime, int totalRestTime) {
        String sql = "update work_time set work_rest_time = ? where pis = '?'";
        sql = String.format(sql, totalRestTime, workTime.getPis());
        template.update(sql);
    }

}
