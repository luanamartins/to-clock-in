package org.clock.in.registers;

import org.clock.in.model.ClockIn;

import java.time.LocalDateTime;

public class ClockInCheckerRule implements ProcessChain {

    private ProcessChain chain;

    public void setNextChain(ProcessChain nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) {
        LocalDateTime dateTime = clockIn.getLocalDateTime();
        LocalDateTime lastCheckIn = LocalDateTime.now(); /*(pegar do banco)*/

        //passedAMinute(lastCheckIn, dateTime);
    }

    public boolean passedAMinute(LocalDateTime past, LocalDateTime current) {
        LocalDateTime aMinuteAgo = current.minusMinutes(1);
        return (aMinuteAgo.compareTo(past) >= 0);
    }
}
