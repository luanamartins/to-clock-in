package org.clock.in.registers;

import org.clock.in.model.ClockIn;

public class TimeRestedRule implements ProcessChain {

    private ProcessChain chain;

    public void setNextChain(ProcessChain nextChain) {
        this.chain = nextChain;
    }

    public void process(ClockIn clockIn) {

    }
}
