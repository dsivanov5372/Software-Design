package org.example;

import java.util.HashSet;
import java.util.Set;

public class AlertService {
    private final Set<TrafficPatrol> patrols = new HashSet<>();

    public void sendAlert(String message) {
        for (TrafficPatrol patrol : patrols) {
            patrol.getAlert(message);
        }
    }

    public void addPatrol(TrafficPatrol patrol) {
        patrols.add(patrol);
    }
}
