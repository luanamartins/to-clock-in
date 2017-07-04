package org.clock.in.registers;

import org.clock.in.model.ClockIn;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TimeWorkedRule implements ProcessChain {

    private ProcessChain chain;

    public void setNextChain(ProcessChain nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) {

    }

    public boolean isOnWeekend(LocalDate date) {
        // the day of the week spelled out completely
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        String weekDay = simpleDateformat.format(date);
        return weekDay.equals("Saturday") || weekDay.equals("Sunday");
    }

}
