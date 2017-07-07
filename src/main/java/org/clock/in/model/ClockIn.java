package org.clock.in.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClockIn implements Serializable {

    private String pis;
    private LocalDateTime localDateTime;

    public ClockIn(String pis, LocalDateTime localDateTime) {
        this.pis = pis;
        this.localDateTime = localDateTime;
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
}
