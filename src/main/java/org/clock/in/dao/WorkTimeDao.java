package org.clock.in.dao;

import org.clock.in.model.WorkTime;

import java.time.LocalDateTime;

public interface WorkTimeDao {
    boolean insert(WorkTime workTime);
    WorkTime get(LocalDateTime date, String pis);
    void updateIsResting(LocalDateTime date, String pis, boolean isResting);
    void updateWorkRestTime(WorkTime workTime, int totalRestTime);
}
