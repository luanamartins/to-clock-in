package org.clock.in.registers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ClockInCheckerRuleTest {

    DateTimeFormatter formatter;
    ClockInCheckerRule ruler;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ruler = new ClockInCheckerRule();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void passedAMinuteTest() {
        LocalDateTime currentTime = LocalDateTime.parse("2016-11-09 10:30:20", formatter);

        LocalDateTime currentTimePassTwoSec = LocalDateTime.parse("2016-11-09 10:31:22", formatter);

        boolean aMinutePassed = ruler.passedAMinute(currentTime, currentTimePassTwoSec);

        Assertions.assertTrue(aMinutePassed);
    }

    @Test
    void notPassedAMinuteTest() {

        LocalDateTime currentTime = LocalDateTime.parse("2016-11-09 10:30:20", formatter);

        LocalDateTime currentTimePassTwoSec = LocalDateTime.parse("2016-11-09 10:30:22", formatter);

        boolean aMinutePassed = ruler.passedAMinute(currentTime, currentTimePassTwoSec);

        Assertions.assertFalse(aMinutePassed);

    }

}