package org.clock.in.registers.rules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TimeRestedRuleTest {

    DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void process() {
        LocalDateTime currentTime = LocalDateTime.parse("2016-11-09 15:40:00", formatter);
        LocalDateTime past = LocalDateTime.parse("2016-11-09 16:10:00", formatter);
        long diffInMinutes = java.time.Duration.between(currentTime, past).toMinutes();
        long diffInHours = java.time.Duration.between(currentTime, past).toHours();
        System.out.println(diffInHours);
        System.out.println(diffInMinutes);
    }


}