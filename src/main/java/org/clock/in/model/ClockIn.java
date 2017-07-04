package org.clock.in.model;

import java.time.LocalDateTime;

public class ClockIn {

    private String pis;
    private LocalDateTime dateTime;

    public ClockIn(String pis, LocalDateTime dateTime) {
        this.pis = pis;
        this.dateTime = dateTime;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
