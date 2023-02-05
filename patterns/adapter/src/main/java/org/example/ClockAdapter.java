package org.example;

import java.time.LocalTime;

public class ClockAdapter {
    public static DigitalWatch getTime(Clocks clock) {
        int hours = clock.getHours() + (clock.getIsDay() ? 12 : 0);
        return new DigitalWatch(LocalTime.of(hours, clock.getMinutes()));
    }
}
