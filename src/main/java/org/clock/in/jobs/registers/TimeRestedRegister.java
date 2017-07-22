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

    public void calculateRestTimeInMinutes(String pis, LocalDateTime localDateTime) {
        List<ClockIn> marks = clockInDao.get(localDateTime, pis);
        WorkTime workTime = workTimeDao.get(localDateTime, pis);
        int restMinutes = calculateRestTimeInMinutes(marks, workTime.isFirstIn());
        workTimeDao.updateWorkRestTime(workTime, restMinutes);
    }

    public int calculateRestTimeInMinutes(List<ClockIn> marks, boolean considerFirstMark) {
        int restMinutes = 0;
        if (marks != null) {

            int i = 0;
            if (!considerFirstMark) {
                i++;
            }

            int firstRestTime = i + 1;
            ClockIn previousClockIn = marks.get(firstRestTime);
            LocalDateTime previous = previousClockIn.getLocalDateTime();
            LocalDateTime current = marks.get(i + 1).getLocalDateTime();

            for (; i < marks.size() - 1; i++) {
                previous = marks.get(i).getLocalDateTime();
                current = marks.get(i + 1).getLocalDateTime();
                restMinutes += timeUtil.diffInMinutes(current, previous);
                i++;
            }
        }
        return restMinutes;
    }

}
