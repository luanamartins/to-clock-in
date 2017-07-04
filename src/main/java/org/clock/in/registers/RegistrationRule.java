package org.clock.in.registers;

import org.clock.in.model.ClockIn;

public interface RegistrationRule {

    boolean process(ClockIn clockIn);

}

