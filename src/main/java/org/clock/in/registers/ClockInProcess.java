package org.clock.in.registers;

import org.clock.in.model.ClockIn;
import org.clock.in.registers.rules.ClockInCheckerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClockInProcess {

    @Autowired
    ClockInCheckerRule rule;

    public void process(ClockIn clockIn) throws ChainException {
        rule.process(clockIn);
    }
}
