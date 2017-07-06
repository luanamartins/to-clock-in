package org.clock.in.dao;

import org.clock.in.model.ClockIn;

import java.time.LocalDateTime;
import java.util.List;

public interface ClockInDao {
    boolean insert(ClockIn clockIn);
    List<ClockIn> get(LocalDateTime date, String pis);
    LocalDateTime getLastClockIn(LocalDateTime date, String pis);
}
