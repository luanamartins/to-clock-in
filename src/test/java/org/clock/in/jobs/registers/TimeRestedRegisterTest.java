package org.clock.in.jobs.registers;

import org.clock.in.model.ClockIn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class TimeRestedRegisterTest {

    @Autowired
    private TimeRestedRegister register;

    private DateTimeFormatter formatter;
    private List<ClockIn> clockInList;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String pis = "1234";
        LocalDateTime time1 = LocalDateTime.parse("2016-11-09 08:00:00", formatter);
        LocalDateTime time2 = LocalDateTime.parse("2016-11-09 12:00:00", formatter);
        LocalDateTime time3 = LocalDateTime.parse("2016-11-09 13:30:00", formatter);
        LocalDateTime time4 = LocalDateTime.parse("2016-11-09 18:10:00", formatter);

        clockInList = new ArrayList<>();
        clockInList.add(new ClockIn(pis, time1));
        clockInList.add(new ClockIn(pis, time2));
        clockInList.add(new ClockIn(pis, time3));
        clockInList.add(new ClockIn(pis, time4));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void registerRestTime() {
        LocalDateTime currentTime = LocalDateTime.parse("2016-11-09 15:40:00", formatter);
        LocalDateTime past = LocalDateTime.parse("2016-11-09 16:10:00", formatter);
        int result = register.calculateRestTimeInMinutes(clockInList, false);

        Assert.isTrue(result == 90);
    }
}