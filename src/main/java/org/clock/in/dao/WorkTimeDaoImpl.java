package org.clock.in.dao;

import org.clock.in.model.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class WorkTimeDaoImpl implements WorkTimeDao {

    @Autowired
    private JdbcWorkTimeDao dao;

    public boolean insert(WorkTime workTime) {
        return dao.insert(workTime);
    }

    public WorkTime get(LocalDateTime date, String pis) {
        return dao.get(date, pis);
    }

    public void updateIsResting(LocalDateTime date, String pis, boolean isResting){
        dao.updateIsResting(date, pis, isResting);
    }

    public void updateWorkRestTime(WorkTime workTime, int totalRestTime){
        dao.updateWorkRestTime(workTime, totalRestTime);
    }

}
