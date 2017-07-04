package org.clock.in.dao;

import org.clock.in.model.ClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClockInDaoImpl implements ClockInDao {

    @Autowired
    JdbcClockInDao daoImpl;

    public boolean insert(ClockIn clockIn) {
        return daoImpl.insert(clockIn);
    }

}
