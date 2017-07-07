package org.clock.in.dao;

import org.clock.in.model.WorkTime;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkTimeDao {
    boolean insert(WorkTime workTime);
    WorkTime get(LocalDateTime date, String pis);
    void updateFirstIn(String pis, boolean firstIn);
    void updateWorkRestTime(WorkTime workTime, int totalRestTime);
}
