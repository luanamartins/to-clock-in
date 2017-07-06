package org.clock.in.dao;

import org.clock.in.mapper.WorkTimeRowMapper;
import org.clock.in.model.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class JdbcWorkTimeDao {

    @Autowired
    private JdbcTemplate template;

    public boolean insert(WorkTime workTime) {
        String sql = "insert into work_time (pis, work_date, work_hours, work_minutes, work_rest_minutes) values (?, ?, ?, ?, ?)";
        template.update(sql, new Object[]{workTime.getPis(), workTime.getLocalDateTime().toString(), 0, 0, 0});
        return true;
    }

    public WorkTime get(LocalDateTime date, String pis) {
        String sql = "select * from work_time where date(work_date) = '?' and pis = '?'";
        sql = String.format(sql, date.toString(), pis);
        Object workTime = template.queryForObject(sql, new WorkTimeRowMapper());
        if (workTime == null) return null;

        return (WorkTime) template.queryForObject(sql, new WorkTimeRowMapper());
    }

    public void updateIsResting(LocalDateTime date, String pis, boolean isResting) {
        String sql = "update work_time set is_resting = ? where pis = '?'";
        sql = String.format(sql, isResting, pis);
        template.update(sql);
    }

    public void updateWorkRestTime(WorkTime workTime, int totalRestTime) {
        String sql = "update work_time set work_rest_time = ? where pis = '?'";
        sql = String.format(sql, totalRestTime, workTime.getPis());
        template.update(sql);
    }

}
