package org.clock.in.registers;

import org.clock.in.model.ClockIn;

public interface ProcessChain {

    void process(ClockIn clockIn);
    void setNextChain(ProcessChain process);

}

