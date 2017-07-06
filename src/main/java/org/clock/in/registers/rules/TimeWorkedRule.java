package org.clock.in.registers.rules;

import org.clock.in.dao.ClockInDaoImpl;
import org.clock.in.dao.WorkTimeDaoImpl;
import org.clock.in.model.ClockIn;
import org.clock.in.model.WorkTime;
import org.clock.in.registers.ChainException;
import org.clock.in.registers.ChainRule;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimeWorkedRule implements ChainRule {

    private ChainRule chain;

    @Autowired
    private ClockInDaoImpl clockInDao;

    @Autowired
    private WorkTimeDaoImpl workTimeDao;

    public void setNextChain(ChainRule nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) throws ChainException {

        String pis = clockIn.getPis();
        LocalDateTime localDateTime = clockIn.getLocalDateTime();
        WorkTime worktime = workTimeDao.get(localDateTime, pis);

        String weekDay = getWeekDay(localDateTime);
        float rateOfWeekDay = getRateOfWeekDay(localDateTime, weekDay);
        LocalDateTime lastTime = clockInDao.getLastClockIn(localDateTime, pis);

        boolean firstClockInOfTheDay = (lastTime == null);

        if (worktime.isResting()) {

            long diffInMinutes = java.time.Duration.between(localDateTime, lastTime).toMinutes();
            long diffInHours = java.time.Duration.between(localDateTime, lastTime).toHours();
        }

        chain.process(clockIn);
    }

    private void sortByDate(List<ClockIn> clocks) {
        Collections.sort(clocks, new Comparator<ClockIn>() {
            public int compare(ClockIn clock1, ClockIn clock2) {
                LocalDateTime t1 = clock1.getLocalDateTime();
                LocalDateTime t2 = clock2.getLocalDateTime();
                return t1.compareTo(t2);
            }
        });
    }

    public String getWeekDay(LocalDateTime dateTime) {
        // the day of the week spelled out completely
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(dateTime);
    }

    private float getRateOfWeekDay(LocalDateTime time, String weekDay) {
        float rate = 1f;
        if ("Saturday".equals(weekDay)) {
            rate = 1.5f;
        }

        if ("Sunday".equals(weekDay)) {
            rate = 2f;
        }
        return rate;
    }


}
