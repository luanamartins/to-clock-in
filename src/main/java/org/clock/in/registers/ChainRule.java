package org.clock.in.registers;

import org.clock.in.model.ClockIn;

public interface ChainRule {

    void process(ClockIn clockIn) throws ChainException;
    void setNextChain(ChainRule process);

}

