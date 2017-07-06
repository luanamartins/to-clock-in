package org.clock.in.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeUtil {

    public long diffInMinutes(LocalDateTime date1, LocalDateTime date2){
        return java.time.Duration.between(date1, date2).toMinutes();
    }
}
