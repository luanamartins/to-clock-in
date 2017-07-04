package org.clock.in.dao;

import org.clock.in.model.ClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcClockInDao {

    @Autowired
    private JdbcTemplate template;

    public boolean insert(ClockIn clockIn) {
        String sql = "INSERT INTO clock_in (PIS, CLOCKIN_DATE) VALUES (?, ?)";

        template.update(sql, new Object[]{clockIn.getPis(), clockIn.getDateTime().toString()});
        return true;
    }

}
