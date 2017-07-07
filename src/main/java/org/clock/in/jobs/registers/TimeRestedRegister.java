package org.clock.in.jobs.registers;

import org.clock.in.dao.ClockInDaoImpl;
import org.clock.in.dao.WorkTimeDaoImpl;
import org.clock.in.model.ClockIn;
import org.clock.in.model.WorkTime;
import org.clock.in.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@PropertySource("classpath:config.properties")
public class TimeRestedRegister {

    @Autowired
    private ClockInDaoImpl clockInDao;

    @Autowired
    private WorkTimeDaoImpl workTimeDao;

    @Autowired
    private TimeUtil timeUtil;

    public void registerRestTime(String pis, LocalDateTime localDateTime){
        List<ClockIn> marks = clockInDao.get(localDateTime, pis);
        WorkTime workTime = workTimeDao.get(localDateTime, pis);
        registerRestTime(marks, workTime);
    }

    public void registerRestTime(List<ClockIn> marks, WorkTime workTime) {
        if (marks != null) {
            boolean considerFirstMark = workTime.isFirstIn();

            int i = 0;
            if (!considerFirstMark) {
                i++;
            }

            ClockIn first = marks.get(i);
            LocalDateTime current = first.getLocalDateTime();

            int restMinutes = 0;
            for (; i < marks.size() - 1; i++) {
                timeUtil.diffInMinutes(current);
            }
        }
    }

}
