package org.clock.in.dao;

import org.clock.in.model.ClockIn;

public interface ClockInDao {
    boolean insertClockIn(ClockIn clockIn);
}
