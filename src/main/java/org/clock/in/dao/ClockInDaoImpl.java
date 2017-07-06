package org.clock.in.dao;

import org.clock.in.model.ClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ClockInDaoImpl implements ClockInDao {

    @Autowired
    JdbcClockInDao daoImpl;

    public boolean insert(ClockIn clockIn) {
        return daoImpl.insert(clockIn);
    }

    public List<ClockIn> get(LocalDateTime date, String pis){
        return daoImpl.get(date, pis);
    }

    public LocalDateTime getLastClockIn(LocalDateTime date, String pis){
        return daoImpl.getLastClockIn(date, pis);
    }

}
