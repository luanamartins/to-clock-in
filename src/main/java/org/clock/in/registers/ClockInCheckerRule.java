package org.clock.in.registers;

import org.clock.in.model.ClockIn;

import java.time.LocalDateTime;

public class ClockInCheckerRule implements RegistrationRule {

    public boolean process(ClockIn clockIn){
        LocalDateTime dateTime = clockIn.getLocalDateTime();
        LocalDateTime lastCheckIn = LocalDateTime.now(); /*(pegar do banco)*/
        return passedAMinute(lastCheckIn, dateTime);
    }

    public boolean passedAMinute(LocalDateTime past, LocalDateTime current) {
        LocalDateTime aMinuteAgo = current.minusMinutes(1);
        return (aMinuteAgo.compareTo(past) >= 0);
    }
}
