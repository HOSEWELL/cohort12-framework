package app.model;

import java.io.Serializable;

public class Schedule implements Serializable {

    private String scheduleType = "Weekday Only Classes";

    private String scheduleTime = "6:00 PM - 9:00 PM";

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
