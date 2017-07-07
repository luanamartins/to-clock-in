package org.clock.in.registers.rules;

import org.clock.in.dao.ClockInDaoImpl;
import org.clock.in.dao.WorkTimeDaoImpl;
import org.clock.in.model.ClockIn;
import org.clock.in.model.WorkTime;
import org.clock.in.registers.ChainException;
import org.clock.in.registers.ChainRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClockInCheckerRule implements ChainRule {

    private ChainRule chain;

    @Autowired
    private ClockInDaoImpl clockInDao;

    @Autowired
    private WorkTimeDaoImpl workTimeDao;

    public void setNextChain(ChainRule nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) throws ChainException {

        LocalDateTime dateTime = clockIn.getLocalDateTime();
        String pis = clockIn.getPis();

        LocalDateTime lastCheckIn = clockInDao.getLastClockIn(dateTime, clockIn.getPis());

        boolean firstClockInOfTheDay = lastCheckIn == null;

        if (firstClockInOfTheDay) {
            clockInDao.insert(clockIn);
            workTimeDao.insert(new WorkTime(pis, dateTime));
        } else {
            if (passedAMinute(lastCheckIn, dateTime)) {
                clockInDao.insert(clockIn);
            } else {
                throw new ChainException("Less than a minute");
            }
        }

        if(chain != null) {
            chain.process(clockIn);
        }
    }

    public boolean passedAMinute(LocalDateTime past, LocalDateTime current) {
        LocalDateTime aMinuteAgo = current.minusMinutes(1);
        return (aMinuteAgo.compareTo(past) >= 0);
    }
}
