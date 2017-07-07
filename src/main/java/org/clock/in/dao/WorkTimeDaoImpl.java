package org.clock.in.dao;

import org.clock.in.model.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WorkTimeDaoImpl implements WorkTimeDao {

    @Autowired
    private JdbcWorkTimeDao dao;

    public boolean insert(WorkTime workTime) {
        return dao.insert(workTime);
    }

    public WorkTime get(LocalDateTime date, String pis) {
        return dao.get(date, pis);
    }

    public void updateFirstIn(String pis, boolean firstIn) {
        dao.updateFirstIn(pis, firstIn);
    }

    public void updateWorkRestTime(WorkTime workTime, int totalRestTime) {
        dao.updateWorkRestTime(workTime, totalRestTime);
    }

}
