package org.clock.in.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkTime implements Serializable {

    private String pis;
    private LocalDateTime localDateTime;
    private int hours;
    private int minutes;
    private int restMinutes;
    private boolean firstIn;

    public WorkTime(String pis, LocalDateTime localDateTime) {
        this.pis = pis;
        this.localDateTime = localDateTime;
        this.hours = 0;
        this.minutes = 0;
        this.restMinutes = 0;
        this.firstIn = false;
    }

    public WorkTime(String pis, LocalDateTime localDateTime, int hours, int minutes, int restMinutes, boolean firstIn) {
        this.pis = pis;
        this.localDateTime = localDateTime;
        this.hours = hours;
        this.minutes = minutes;
        this.restMinutes = restMinutes;
        this.firstIn = firstIn;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getRestMinutes() {
        return restMinutes;
    }

    public void setRestMinutes(int restMinutes) {
        this.restMinutes = restMinutes;
    }

    public boolean isFirstIn() {
        return firstIn;
    }

    public void setFirstIn(boolean firstIn) {
        this.firstIn = firstIn;
    }
}
