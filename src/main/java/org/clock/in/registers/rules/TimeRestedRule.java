package org.clock.in.registers.rules;

import org.clock.in.dao.ClockInDaoImpl;
import org.clock.in.dao.WorkTimeDaoImpl;
import org.clock.in.model.ClockIn;
import org.clock.in.model.WorkTime;
import org.clock.in.registers.ChainException;
import org.clock.in.registers.ChainRule;
import org.clock.in.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TimeRestedRule implements ChainRule {

    private ChainRule chain;

    @Autowired
    private ClockInDaoImpl clockInDao;

    @Autowired
    private WorkTimeDaoImpl workTimeDao;

    @Autowired
    private TimeUtil timeUtil;

    public void setNextChain(ChainRule nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) throws ChainException {
        LocalDateTime currentTime = clockIn.getLocalDateTime();
        LocalDateTime lastTime = clockInDao.getLastClockIn(currentTime, clockIn.getPis());
        WorkTime workTime = workTimeDao.get(clockIn.getLocalDateTime(), clockIn.getPis());

        boolean firstClockInOfTheDay = lastTime == null;

        if(!firstClockInOfTheDay && !workTime.isResting()){
            int restTime = (int) timeUtil.diffInMinutes(currentTime, lastTime);
            int totalRestTime = restTime + workTime.getRestMinutes();
            workTimeDao.updateWorkRestTime(workTime, totalRestTime);

            // X = pega a diferença entre o clock atual e last clock (até meia-noite)
            // soma work_rest + X
            // atualiza worktime (work_rest_minutes)
        }

        chain.process(clockIn);
    }
}
